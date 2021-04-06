package ru.altf000.multimodule.common_entities.server.content

import com.google.gson.annotations.SerializedName

enum class Kind(val value: Int) {
    @SerializedName("kind")
    EMPTY(0),
    @SerializedName("kind")
    MOVIE(1),
    @SerializedName("kind")
    SERIAL(2)
}