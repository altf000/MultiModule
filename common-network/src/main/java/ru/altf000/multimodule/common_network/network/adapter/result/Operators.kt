package ru.altf000.multimodule.common_network.network.adapter.result

import ru.altf000.multimodule.common_network.network.adapter.RequestResult

fun <T> RequestResult<T>.isSuccess() = this is RequestResult.Success

fun <T> RequestResult<T>.asSuccess() = this as RequestResult.Success<T>

fun <T> RequestResult<T>.asFailure() = this as RequestResult.Failure<*>

fun <T, R> RequestResult<T>.mapIfSuccess(transform: (value: T) -> R) = when (this) {
    is RequestResult.Success -> RequestResult.Success.Value(transform(value))
    is RequestResult.Failure<*> -> this
}
