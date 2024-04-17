package kanti.curriewer.data.model.currency.datasource.local

import kanti.curriewer.data.model.currency.RangeAccuracy
import kanti.curriewer.data.model.currency.datasource.CurrencyWithTime
import kanti.curriewer.shared.result.DataResult
import kanti.curriewer.shared.result.LocalError
import kotlinx.datetime.Instant

interface CurrencyValuesLocalDataSource {

	suspend fun replace(currenciesWithTime: List<CurrencyWithTime>)

	suspend fun getCurrencyRanges(
		baseCurrencyCode: String,
		start: Instant,
		end: Instant,
		accuracy: RangeAccuracy
	): DataResult<Map<String, List<CurrencyWithTime>>, LocalError>
}