package kanti.curriewer.data.model.currency

import kanti.curriewer.shared.result.DataError
import kanti.curriewer.shared.result.DataResult
import kotlinx.datetime.Instant
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor() : CurrencyRepository {

	override suspend fun getTitleByCode(currencyCode: String): DataResult<String, DataError> {
		TODO("Not yet implemented")
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