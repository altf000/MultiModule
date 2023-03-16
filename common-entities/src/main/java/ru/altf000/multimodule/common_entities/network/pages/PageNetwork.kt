package ru.altf000.multimodule.common_entities.network.pages

import com.google.gson.annotations.SerializedName

data class PageNetwork(
    @SerializedName("blocks") val blocks: List<BlockNetwork> = emptyList(),
)
