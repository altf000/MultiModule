package ru.altf000.multimodule.common_entities.server.content

import com.google.gson.annotations.SerializedName

data class FullContentResponse(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("title") val title: String? = null,
    @SerializedName("synopsis") val synopsis: String? = null,
    @SerializedName("posters") val posters: List<Poster> = emptyList(),
    @SerializedName("thumbs") val thumbs: List<Poster> = emptyList(),
    @SerializedName("rating") val rating: RatingRoot? = null,
    @SerializedName("restrict") val restrict: Int = -1,
    @SerializedName("year") val year: Int = -1,
    @SerializedName("kind") val kind: Int = -1,
    @SerializedName("genres") val genres: List<Int> = emptyList(),
    @SerializedName("country") val country: Int = -1
)