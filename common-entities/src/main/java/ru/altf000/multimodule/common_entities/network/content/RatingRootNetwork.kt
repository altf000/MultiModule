package ru.altf000.multimodule.common_entities.network.content

import com.google.gson.annotations.SerializedName

data class RatingRootNetwork(
    @SerializedName("ready") val ready: RatingNetwork? = null,
)