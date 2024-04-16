package kanti.curriewer.data.model.currency

import kanti.curriewer.data.model.currency.datasource.remote.CurrencyRemoteDataSource
import kanti.curriewer.data.model.currency.datasource.local.CurrencyDataLocalDataSource
import kanti.curriewer.data.model.currency.datasource.local.CurrencyValuesLocalDataSource
import kanti.curriewer.shared.result.DataError
import kanti.curriewer.shared.result.DataResult
import kanti.curriewer.shared.result.NotFoundError
import kanti.curriewer.shared.result.alsoIfNotError
import kanti.curriewer.shared.result.runIfNotError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.Instant
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
	private val valueLocalDataSource: CurrencyValuesLocalDataSource,
	private val dataLocalDataSource: CurrencyDataLocalDataSource,
	private val remoteDataSource: CurrencyRemoteDataSource
) : CurrencyRepository {

	override suspend fun getTitleByCode(
		currencyCode: String
	): DataResult<String, DataError> = withContext(Dispatchers.Default) {
		var localResult = dataLocalDataSource.getTitle(currencyCode)

		if (localResult.error is NotFoundError) {
			val remoteResult = remoteDataSource.getAllCurrenciesData()

			val remoteError = remoteResult.error
			if (remoteError != null)
				return@withContext DataResult.Error(remoteError, localResult.value)

			remoteResult.alsoIfNotError {
				dataLocalDataSource.replace(it)
			}

			localResult = dataLocalDataSource.getTitle(currencyCode)
		}

		localResult.runIfNotError { DataResult.Success(it) }
	}

	override suspend fun getAllCurrencyCodes(): DataResult<Sequence<String>, DataError> {
		TODO("Not yet implemented")
	}

	override suspend fun getRange(
		baseCurrencyCode: String,
		currencyCode: String,
		end: Instant,
		start: Instant,
		accuracy: RangeAccuracy
	): DataResult<List<CurrencyWithTime>, DataError> {
		TODO("Not yet implemented")
	}
}