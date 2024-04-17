package kanti.curriewer.data.model.currency

import kanti.curriewer.shared.minusDay
import kanti.curriewer.shared.result.DataError
import kanti.curriewer.shared.result.DataResult
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

interface CurrencyRepository {

	suspend fun getTitleByCode(currencyCode: String): DataResult<String, DataError>

	suspend fun getAllSpans(
		baseCurrencyCode: String,
		end: Instant = Clock.System.now(),
		start: Instant = end.minusDay(),
		accuracy: RangeAccuracy = RangeAccuracy.DEFAULT
	): DataResult<List<CurrencySpan>, DataError>
}