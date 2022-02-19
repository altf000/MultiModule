package ru.altf000.multimodule.common_entities.network.categories

import com.google.gson.annotations.SerializedName

data class CategoryNetwork(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("title") val title: String? = null,
    @SerializedName("genres") val genres: MutableList<GenreNetwork>
)