package kanti.curriewer.data.model.currency.datasource.remote

import kanti.curriewer.data.model.currency.CurrencyData
import kanti.curriewer.shared.result.DataResult
import kanti.curriewer.shared.result.RemoteError

interface CurrencyRemoteDataSource {

	suspend fun getAllCurrenciesData(): DataResult<List<CurrencyData>, RemoteError>
}