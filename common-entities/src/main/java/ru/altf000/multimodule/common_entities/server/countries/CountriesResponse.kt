package ru.altf000.multimodule.common_entities.server.countries

import com.google.gson.annotations.SerializedName

data class CountriesResponse(
    @SerializedName("result") val result: Map<String, Country> = emptyMap()
)