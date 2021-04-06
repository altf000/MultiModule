package ru.altf000.multimodule.common_entities.server.countries

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("title") val title: String? = null,
    @SerializedName("code") val code: String? = null
)