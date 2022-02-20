package ru.altf000.multimodule.common_entities.network.pages

import com.google.gson.annotations.SerializedName

enum class BlockType(val value: String) {
    @SerializedName("promo")
    PROMO("promo"),

    @SerializedName("collection")
    COLLECTION("collection"),

    @SerializedName("")
    UNDEFIEND("")
}
