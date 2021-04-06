package ru.altf000.multimodule.common_entities.server.content

import com.google.gson.annotations.SerializedName

data class ContentResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String? = null,
    @SerializedName("synopsis") val synopsis: String? = null,
    @SerializedName("posters") val posters: List<Poster> = emptyList(),
    @SerializedName("rating") val rating: RatingRoot? = null,
    @SerializedName("restrict") val restrict: Int,
    @SerializedName("year") val year: Int,
    @SerializedName("kind") val kind: Kind
)