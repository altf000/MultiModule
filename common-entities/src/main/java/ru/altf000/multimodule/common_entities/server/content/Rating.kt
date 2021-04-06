package ru.altf000.multimodule.common_entities.server.content

import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("main") val main: Float
)