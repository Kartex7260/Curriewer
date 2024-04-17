package kanti.curriewer.data.model.currency.datasource.local

import kanti.curriewer.data.model.currency.datasource.CurrencyData
import kanti.curriewer.shared.result.DataResult
import kanti.curriewer.shared.result.LocalError

interface CurrencyDataLocalDataSource {

	suspend fun getCurrenciesData(): DataResult<List<CurrencyData>, LocalError>

	suspend fun getTitle(currencyCode: String): DataResult<String, LocalError>

	suspend fun replace(currenciesData: List<CurrencyData>)
}