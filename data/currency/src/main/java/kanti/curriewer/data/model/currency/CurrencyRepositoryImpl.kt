package kanti.curriewer.data.model.currency

import kanti.curriewer.data.model.currency.datasource.local.CurrencyDataLocalDataSource
import kanti.curriewer.data.model.currency.datasource.local.CurrencyValuesLocalDataSource
import kanti.curriewer.data.model.currency.datasource.remote.CurrencyRemoteDataSource
import kanti.curriewer.shared.result.DataError
import kanti.curriewer.shared.result.DataResult
import kanti.curriewer.shared.result.NotFoundError
import kanti.curriewer.shared.result.ValueIsNullError
import kanti.curriewer.shared.result.alsoIfNotError
import kanti.curriewer.shared.result.runIfNotError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
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
		var localResult = dataLocalDataSource.getTitle(currencyCode = currencyCode)

		if (localResult.error is NotFoundError) {
			val error = loadCurrenciesData()
			if (error != null)
				return@withContext DataResult.Error(error)

			localResult = dataLocalDataSource.getTitle(currencyCode = currencyCode)
		}

		localResult.runIfNotError { DataResult.Success(it) }
	}

	override suspend fun getAllSpans(
		baseCurrencyCode: String,
		end: Instant,
		start: Instant,
		accuracy: RangeAccuracy
	): DataResult<List<CurrencySpan>, DataError> {
		TODO("Not yet implemented")
	}

	private suspend fun loadCurrenciesData(): DataError? {
		val remoteResult = remoteDataSource.getCurrenciesData()

		val error = remoteResult.error
		if (error != null)
			return error

		val value = remoteResult.value
			?: return ValueIsNullError("${CurrencyRepositoryImpl::class.java.name}: " +
				"loadCurrenciesData()")

		dataLocalDataSource.replace(value)
		return null
	}
}