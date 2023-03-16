package ru.altf000.multimodule.common_network.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.altf000.multimodule.common_network.network.TimberOkHttpLogger
import ru.altf000.multimodule.common_network.network.adapter.result.ResultAdapterFactory
import ru.altf000.multimodule.common_network.network.api.ApiService
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://api.ivi.ru/mobileapi/"
const val CONNECT_TIMEOUT = 10L
const val WRITE_TIMEOUT = 60L
const val READ_TIMEOUT = 60L

val networkModule = module {
    single {
        HttpLoggingInterceptor(TimberOkHttpLogger()).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single { buildOkHttp(get<HttpLoggingInterceptor>()) }
    single { buildRetrofit<ApiService>(get()) }
}

private fun buildOkHttp(interceptor: Interceptor) = OkHttpClient.Builder()
    .addNetworkInterceptor(interceptor)
    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
    .build()

private inline fun <reified T> buildRetrofit(
    okHttpClient: OkHttpClient,
): T = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(ResultAdapterFactory())
    .build()
    .create(T::class.java)