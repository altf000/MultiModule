package ru.altf000.multimodule.common_entities.network.countries

import com.google.gson.annotations.SerializedName

data class CountryNetwork(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("title") val title: String? = null,
    @SerializedName("code") val code: String? = null,
)