package kanti.curriewer.data.model.currency.datasource.remote

import kanti.curriewer.data.model.currency.CurrencyData
import kanti.curriewer.data.model.currency.CurrencyWithTime
import kanti.curriewer.data.model.currency.RangeAccuracy
import kanti.curriewer.shared.result.DataResult
import kanti.curriewer.shared.result.RemoteError
import kotlinx.datetime.Instant

interface CurrencyRemoteDataSource {

	suspend fun getAllCurrenciesData(): DataResult<List<CurrencyData>, RemoteError>

	suspend fun getRange(
		baseCurrencyCode: String,
		currencyCode: String,
		end: Instant,
		start: Instant,
		accuracy: RangeAccuracy
	): DataResult<List<CurrencyWithTime>, RemoteError>

	suspend fun getRange(
		baseCurrencyCode: String,
		end: Instant,
		start: Instant,
		accuracy: RangeAccuracy
	): DataResult<Map<String, CurrencyWithTime>, RemoteError>
}