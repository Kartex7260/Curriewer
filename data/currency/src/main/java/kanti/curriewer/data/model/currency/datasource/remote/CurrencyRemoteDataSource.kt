package kanti.curriewer.data.model.currency.datasource.remote

import kanti.curriewer.data.model.currency.datasource.CurrencyData
import kanti.curriewer.data.model.currency.datasource.CurrencyWithTime
import kanti.curriewer.data.model.currency.RangeAccuracy
import kanti.curriewer.shared.result.DataResult
import kanti.curriewer.shared.result.RemoteError
import kotlinx.datetime.Instant

interface CurrencyRemoteDataSource {

	suspend fun getCurrenciesData(): DataResult<List<CurrencyData>, RemoteError>

	suspend fun getCurrencyRanges(
		baseCurrencyCode: String,
		start: Instant,
		end: Instant,
		accuracy: RangeAccuracy
	): DataResult<Map<String, List<CurrencyWithTime>>, RemoteError>
}