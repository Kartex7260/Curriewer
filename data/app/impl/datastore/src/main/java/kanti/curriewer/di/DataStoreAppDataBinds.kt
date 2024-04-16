package kanti.curriewer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kanti.curriewer.data.app.AppDataRepository
import kanti.curriewer.data.app.datastore.DataStoreAppDataRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataStoreAppDataBinds {

	@Binds
	fun bindDataStoreAppDataRepository(repository: DataStoreAppDataRepository): AppDataRepository
}