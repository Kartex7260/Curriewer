package kanti.curriewer.data.model.currency

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus

interface CurrencyRepository {

	suspend fun getRange(
		baseCurrencyCode: String,
		currencyCode: String,
		end: Instant = Clock.System.now(),
		start: Instant = end.minus(value = 1, unit = DateTimeUnit.DAY, timeZone = TimeZone.UTC),
		accuracy: RangeAccuracy = RangeAccuracy.DEFAULT
	): List<CurrencyWithTime>
}