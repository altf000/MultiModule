package ru.altf000.multimodule.common_entities.network.content

import com.google.gson.annotations.SerializedName

data class PosterNetwork(
    @SerializedName("height") val height: Int,
    @SerializedName("width") val width: Int,
    @SerializedName("content_format") val contentFormat: String? = null,
    @SerializedName("url") val url: String? = null
)