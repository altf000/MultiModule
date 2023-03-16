package ru.altf000.multimodule.common_entities.network.countries

import com.google.gson.annotations.SerializedName

data class CountriesResultNetwork(
    @SerializedName("result") val result: Map<String, CountryNetwork> = emptyMap(),
)