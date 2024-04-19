package kanti.curriewer.data.retrofit

import kanti.curriewer.data.retrofit.currency.CurrencyService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

private const val baseLink = "https://api.currencyapi.com/v3/"
private const val apiKeyName = "apikey"

fun retrofitBuilder(): Retrofit = Retrofit.Builder()
	.baseUrl(baseLink)
	.client(httpBuilder())
	.build()

fun currencyServiceBuilder(retrofit: Retrofit): CurrencyService = retrofit
	.create(CurrencyService::class.java)

fun httpBuilder(): OkHttpClient = OkHttpClient.Builder()
	.addInterceptor(apiKeyInterceptor)
	.build()

val apiKeyInterceptor: Interceptor = Interceptor {
	val request = it.request().newBuilder()
		.addHeader(apiKeyName, BuildConfig.CurrencyAPI)
		.build()
	it.proceed(request)
}