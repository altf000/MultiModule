package ru.altf000.multimodule.common_entities.server.content

import com.google.gson.annotations.SerializedName

data class Poster(
    @SerializedName("height") val height: Int,
    @SerializedName("width") val width: Int,
    @SerializedName("content_format") val contentFormat: String? = null,
    @SerializedName("url") val url: String? = null
)