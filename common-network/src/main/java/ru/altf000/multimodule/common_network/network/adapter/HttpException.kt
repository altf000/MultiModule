package ru.altf000.multimodule.common_network.network.adapter

class HttpException(
    val statusCode: Int,
    val statusMessage: String? = null,
    val url: String? = null,
    cause: Throwable? = null,
) : Exception(null, cause)