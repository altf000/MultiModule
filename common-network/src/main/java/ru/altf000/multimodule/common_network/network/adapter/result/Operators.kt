package ru.altf000.multimodule.common_network.network.adapter.result

import ru.altf000.multimodule.common_network.network.adapter.RequestResult

fun <T> RequestResult<T>.isSuccess(): Boolean {
    return this is RequestResult.Success
}

fun <T> RequestResult<T>.asSuccess(): RequestResult.Success<T> {
    return this as RequestResult.Success<T>
}

fun <T> RequestResult<T>.asFailure(): RequestResult.Failure<*> {
    return this as RequestResult.Failure<*>
}

fun <T, R> RequestResult<T>.map(transform: (value: T) -> R): RequestResult<R> {
    return when (this) {
        is RequestResult.Success -> RequestResult.Success.Value(transform(value))
        is RequestResult.Failure<*> -> this
    }
}