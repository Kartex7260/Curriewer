package kanti.curriewer.data.appdata

import kotlinx.coroutines.flow.Flow

interface AppDataRepository {

	val data: Flow<AppData>

	suspend fun setBaseCurrencyCode(currencyCode: String)
}