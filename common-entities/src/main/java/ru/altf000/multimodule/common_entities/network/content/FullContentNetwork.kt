package ru.altf000.multimodule.common_entities.network.content

import com.google.gson.annotations.SerializedName

data class FullContentNetwork(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("title") val title: String? = null,
    @SerializedName("synopsis") val synopsis: String? = null,
    @SerializedName("posters") val posters: List<PosterNetwork> = emptyList(),
    @SerializedName("thumbs") val thumbs: List<PosterNetwork> = emptyList(),
    @SerializedName("rating") val rating: RatingRootNetwork? = null,
    @SerializedName("restrict") val restrict: Int = -1,
    @SerializedName("year") val year: Int = -1,
    @SerializedName("kind") val kind: Int = -1,
    @SerializedName("genres") val genres: List<Int> = emptyList(),
    @SerializedName("country") val country: Int = -1
)