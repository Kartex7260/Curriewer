package kanti.curriewer.data.app.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kanti.curriewer.data.app.AppData
import kanti.curriewer.data.app.AppDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "appData")

class DataStoreAppDataRepository @Inject constructor(
	@ApplicationContext private val appContext: Context
) : AppDataRepository {

	private val baseCurrencyCodeKey = stringPreferencesKey("baseCurrencyCode")

	override val appData: Flow<AppData> = appContext.dataStore.data
		.map { preferences ->
			var isBreak = false

			val baseCurrencyCode = preferences[baseCurrencyCodeKey].run {
				if (this == null) {
					isBreak = true
					setDefaultBaseCurrencyCode()
					""
				} else this
			}

			if (isBreak)
				return@map null
			AppData(
				baseCurrencyCode = baseCurrencyCode
			)
		}
		.filterNotNull()

	override suspend fun setBaseCurrencyCode(baseCurrencyCode: String) {
		appContext.dataStore.edit {
			it[baseCurrencyCodeKey] = baseCurrencyCode
		}
	}
}