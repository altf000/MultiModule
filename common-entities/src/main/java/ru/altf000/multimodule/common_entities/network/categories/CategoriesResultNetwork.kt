package ru.altf000.multimodule.common_entities.network.categories

import com.google.gson.annotations.SerializedName

data class CategoriesResultNetwork(
    @SerializedName("result") val result: List<CategoryNetwork> = emptyList()
)