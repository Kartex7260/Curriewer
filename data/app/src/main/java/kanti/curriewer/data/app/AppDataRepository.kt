package kanti.curriewer.data.app

import kotlinx.coroutines.flow.Flow

interface AppDataRepository {

	val appData: Flow<AppData>

	suspend fun setBaseCurrencyCode(baseCurrencyCode: String)

	suspend fun setDefaultBaseCurrencyCode() {
		setBaseCurrencyCode(baseCurrencyCode = BASE_CURRENCY)
	}

	companion object {

		const val BASE_CURRENCY = "USD"
	}
}