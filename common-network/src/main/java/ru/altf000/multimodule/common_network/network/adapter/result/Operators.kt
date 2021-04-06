package ru.altf000.multimodule.common_network.network.adapter.result

import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

fun <T> RequestResult<T>.isSuccess(): Boolean {
    return this is RequestResult.Success
}

fun <T> RequestResult<T>.asSuccess(): RequestResult.Success<T> {
    return this as RequestResult.Success<T>
}

@OptIn(ExperimentalContracts::class)
fun <T> RequestResult<T>.isFailure(): Boolean {
    contract {
        returns(true) implies (this@isFailure is RequestResult.Failure<*>)
    }
    return this is RequestResult.Failure<*>
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

fun <T, R> RequestResult<T>.flatMap(transform: (result: RequestResult<T>) -> RequestResult<R>): RequestResult<R> {
    return transform(this)
}