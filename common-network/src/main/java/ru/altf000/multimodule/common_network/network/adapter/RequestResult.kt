package ru.altf000.multimodule.common_network.network.adapter

typealias SuccessResult<T> = RequestResult.Success<T>
typealias ErrorResult = RequestResult.Failure<*>

sealed class RequestResult<out T> {

    sealed class Success<T> : RequestResult<T>() {

        abstract val value: T

        data class Value<T>(override val value: T) : Success<T>()

        data class HttpSuccess<T>(
            override val value: T,
            override val statusCode: Int,
            override val statusMessage: String? = null,
            override val url: String? = null
        ) : Success<T>(), HttpResponse
    }

    sealed class Failure<E : Throwable>(open val error: E? = null) : RequestResult<Nothing>() {

        data class Error(override val error: Throwable?) : Failure<Throwable>(error)

        data class HttpError(override val error: HttpException) : Failure<HttpException>(), HttpResponse {
            override val statusCode: Int get() = error.statusCode
            override val statusMessage: String? get() = error.statusMessage
            override val url: String? get() = error.url
        }
    }
}