package ru.altf000.multimodule.common_entities.network.content

import com.google.gson.annotations.SerializedName

data class ContentNetwork(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String? = null,
    @SerializedName("synopsis") val synopsis: String? = null,
    @SerializedName("posters") val posters: List<PosterNetwork> = emptyList(),
    @SerializedName("rating") val rating: RatingRootNetwork? = null,
    @SerializedName("restrict") val restrict: Int,
    @SerializedName("year") val year: Int,
    @SerializedName("kind") val kind: Int,
)