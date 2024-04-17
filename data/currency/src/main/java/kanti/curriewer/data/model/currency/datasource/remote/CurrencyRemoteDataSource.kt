package kanti.curriewer.data.model.currency.datasource.remote

import kanti.curriewer.data.model.currency.CurrencyData
import kanti.curriewer.data.model.currency.CurrencyWithTime
import kanti.curriewer.data.model.currency.RangeAccuracy
import kanti.curriewer.shared.result.DataResult
import kanti.curriewer.shared.result.RemoteError
import kotlinx.datetime.Instant

interface CurrencyRemoteDataSource {

	suspend fun getCurrenciesData(): DataResult<List<CurrencyData>, RemoteError>
}