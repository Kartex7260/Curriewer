package kanti.curriewer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kanti.curriewer.data.retrofit.retrofitBuilder
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

	@Provides
	@Singleton
	fun provideRetrofit(): Retrofit = retrofitBuilder()
}