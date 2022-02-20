package ru.altf000.multimodule.common_network.network.adapter

interface HttpResponse {
    val statusCode: Int
    val statusMessage: String?
    val url: String?
}