package ru.altf000.multimodule.common_entities.server.categories

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("title") val title: String? = null
)