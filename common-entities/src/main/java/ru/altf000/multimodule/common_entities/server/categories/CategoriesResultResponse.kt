package ru.altf000.multimodule.common_entities.server.categories

import com.google.gson.annotations.SerializedName

data class CategoriesResultResponse(
    @SerializedName("result") val result: List<Category> = emptyList()
)