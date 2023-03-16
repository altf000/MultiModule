package ru.altf000.multimodule.common_entities.network.pages

import com.google.gson.annotations.SerializedName

data class RequestParamsNetwork(
    @SerializedName("id") val id: Int = -1,
)