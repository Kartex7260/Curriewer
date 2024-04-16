package kanti.curriewer.data.model.currency

import kanti.curriewer.shared.minusDay
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

interface CurrencyRepository {

	suspend fun getAllCurrencyCodes(): Sequence<String>

	suspend fun getRange(
		baseCurrencyCode: String,
		currencyCode: String,
		end: Instant = Clock.System.now(),
		start: Instant = end.minusDay(),
		accuracy: RangeAccuracy = RangeAccuracy.DEFAULT
	): List<CurrencyWithTime>
}