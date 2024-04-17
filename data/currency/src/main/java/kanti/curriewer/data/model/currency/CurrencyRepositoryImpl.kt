package kanti.curriewer.data.model.currency

import kanti.curriewer.data.model.currency.datasource.CurrencyData
import kanti.curriewer.data.model.currency.datasource.local.CurrencyDataLocalDataSource
import kanti.curriewer.data.model.currency.datasource.local.CurrencyValuesLocalDataSource
import kanti.curriewer.data.model.currency.datasource.remote.CurrencyRemoteDataSource
import kanti.curriewer.shared.result.DataError
import kanti.curriewer.shared.result.DataResult
import kanti.curriewer.shared.result.NoConnectionError
import kanti.curriewer.shared.result.NotFoundError
import kanti.curriewer.shared.result.ValueIsNullError
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
	): DataResult<List<CurrencySpan>, DataError> = withContext(Dispatchers.Default) {
		val currenciesDeferred = async {
			remoteDataSource.getCurrencyRanges(
				baseCurrencyCode = baseCurrencyCode,
				start = start,
				end = end,
				accuracy = accuracy
			)
		}
		val titlesDeferred = async { getTitles() }
		currenciesDeferred.start()
		titlesDeferred.start()

		val currenciesResult = currenciesDeferred.await()
		val titlesResult = titlesDeferred.await()

		val titlesError = titlesResult.error
		if (titlesError != null)
			return@withContext DataResult.Error(titlesError)

		val titles = titlesResult.value ?: return@withContext DataResult
			.Error(ValueIsNullError(CurrencyRepositoryImpl::class.java.name
						+ ": getAllSpans(): titles"))

		val currenciesError = currenciesResult.error
		if (currenciesError is NoConnectionError) {
			val localResult = valueLocalDataSource.getCurrencyRanges(
				baseCurrencyCode = baseCurrencyCode,
				start = start,
				end = end,
				accuracy = accuracy
			)

			return@withContext DataResult.Error(
				error = currenciesError,
				value = localResult.value
					?.map { it.toCurrencySpan(titles) }
			)
		}

		currenciesResult.runIfNotError { currencies ->
			launch {
				valueLocalDataSource.replace(
					currenciesWithTime = currencies.flatMap { it.value }
				)
			}

			DataResult.Success(
				value = currencies.map { entry ->
					entry.toCurrencySpan(titles)
				}
			)
		}
	}

	private suspend fun getTitles(): DataResult<List<CurrencyData>, DataError> {
		val remoteData = remoteDataSource.getCurrenciesData()

		val remoteError = remoteData.error
		if (remoteError is NoConnectionError) {
			val localResult = dataLocalDataSource.getCurrenciesData()

			return DataResult.Error(
				error = remoteError,
				value = localResult.value
			)
		}

		return remoteData.runIfNotError {
			DataResult.Success(it)
		}
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