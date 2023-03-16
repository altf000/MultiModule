package ru.altf000.multimodule.common_entities.network.categories

import com.google.gson.annotations.SerializedName

data class GenreNetwork(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("title") val title: String? = null,
)