package ru.altf000.multimodule.common_entities.network.pages

import com.google.gson.annotations.SerializedName

data class BlockNetwork(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("title") val title: String = "",
    @SerializedName("type") val type: BlockType = BlockType.UNDEFIEND,
    @SerializedName("request_params") val requestParams: RequestParamsNetwork? = null,
)
