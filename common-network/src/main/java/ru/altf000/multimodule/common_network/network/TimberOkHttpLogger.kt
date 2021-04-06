package ru.altf000.multimodule.common_network.network

import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

class TimberOkHttpLogger() : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Timber.tag("OkHttp").d(message)
    }
}