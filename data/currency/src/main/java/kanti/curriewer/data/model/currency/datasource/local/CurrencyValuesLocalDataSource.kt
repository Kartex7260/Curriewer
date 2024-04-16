package kanti.curriewer.data.model.currency.datasource.local

import kanti.curriewer.data.model.currency.CurrencyWithTime

interface CurrencyValuesLocalDataSource {

	suspend fun replace(currencies: List<CurrencyWithTime>)
}