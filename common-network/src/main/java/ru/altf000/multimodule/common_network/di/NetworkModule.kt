package ru.altf000.multimodule.common_network.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.altf000.multimodule.common_network.network.TimberOkHttpLogger
import ru.altf000.multimodule.common_network.network.adapter.result.ResultAdapterFactory
import ru.altf000.multimodule.common_network.network.api.ApiService
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
internal class NetworkModule {

    companion object {
        private const val BASE_URL = "https://api.ivi.ru/mobileapi/"
        private const val CONNECT_TIMEOUT = 10L
        private const val WRITE_TIMEOUT = 60L
        private const val READ_TIMEOUT = 60L
    }

    @Singleton
    @Provides
    fun createNetworkClient() = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor(TimberOkHttpLogger()).apply { level = HttpLoggingInterceptor.Level.BODY })
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ResultAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun getApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
}