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
		return withContext(Dispatchers.Default) {
			val remoteResult = remoteDataSource.getAllCurrenciesData()

			val remoteError = remoteResult.error
			if (remoteError != null) {
				val localResult = dataLocalDataSource.getAllCurrenciesData()
				return@withContext DataResult.Error(remoteError, localResult.map { it.code })
			}

			remoteResult.runIfNotError { currenciesData ->
				launch {
					dataLocalDataSource.replace(currenciesData)
				}
				DataResult.Success(currenciesData.asSequence().map { it.code })
			}
		}
	}

	override suspend fun getRange(
		baseCurrencyCode: String,
		currencyCode: String,
		end: Instant,
		start: Instant,
		accuracy: RangeAccuracy
	): DataResult<List<CurrencyWithTime>, DataError> = withContext(Dispatchers.Default) {
		val titlesDeferred = async { remoteDataSource.getAllCurrenciesData() }
		val valuesDeferred = async {
			remoteDataSource.getRange(
				baseCurrencyCode = baseCurrencyCode,
				currencyCode = currencyCode,
				end = end,
				start = start,
				accuracy = accuracy
			)
		}
		titlesDeferred.start()
		valuesDeferred.start()

		val titlesResult = titlesDeferred.await()
		val titlesError = titlesResult.error
		if (titlesError != null)
			return@withContext DataResult.Error(titlesError)

		val titles = titlesResult.value ?: return@withContext DataResult
			.Error(ValueIsNullError("${CurrencyRepositoryImpl::class.java.name}: getRange: titles"))
		launch {
			dataLocalDataSource.replace(titles)
		}

		val valuesResult = valuesDeferred.await()
		val valuesError = valuesResult.error
		if (valuesError != null)
			return@withContext DataResult.Error(valuesError)

		val values = valuesResult.value ?: return@withContext DataResult
			.Error(ValueIsNullError("${CurrencyRepositoryImpl::class.java.name}: getRange: values"))
		launch {
			valueLocalDataSource.replace(values)
		}

		return@withContext DataResult.Success(
			value = values
				.map { currency ->
					currency.copy(
						title = titles.firstOrNull { it.code == "" }?.title ?: currency.code
					)
				}
		)
	}
}