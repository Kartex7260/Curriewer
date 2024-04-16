package kanti.curriewer.data.app

import kotlinx.coroutines.flow.Flow

interface AppDataRepository {

	val appData: Flow<AppData>

	suspend fun setBaseCurrencyCode(baseCurrencyCode: String)
}