package ru.altf000.multimodule.common_entities.mapper

import ru.altf000.multimodule.common_entities.domain.FullContent
import ru.altf000.multimodule.common_entities.entity.FullContentEntity
import ru.altf000.multimodule.common_entities.server.content.FullContentResponse
import ru.altf000.multimodule.common_entities.server.content.Kind

fun FullContentResponse.toLocal() = FullContent(
    id = this.id,
    title = this.title.orEmpty(),
    synopsis = this.synopsis.orEmpty(),
    posterUrl = this.posters.first().url.orEmpty(),
    thumbUrl = this.thumbs.first().url.orEmpty(),
    rating = this.rating?.ready?.main.toString(),
    year = this.year,
    restrict = this.restrict,
    isSerial = this.kind == Kind.SERIAL,
    genres = this.genres,
    country = this.country
)


fun FullContentEntity.toLocal() = FullContent(
    id = this.id,
    title = this.title,
    synopsis = this.synopsis,
    posterUrl = this.posterUrl,
    thumbUrl = this.thumbUrl,
    rating = this.rating,
    year = this.year,
    restrict = this.restrict,
    isSerial = this.isSerial,
    genres = this.genres,
    country = this.country
)

fun FullContent.toEntity() = FullContentEntity(
    id = this.id,
    title = this.title,
    synopsis = this.synopsis,
    posterUrl = this.posterUrl,
    thumbUrl = this.thumbUrl,
    rating = this.rating,
    year = this.year,
    restrict = this.restrict,
    isSerial = this.isSerial,
    genres = this.genres,
    country = this.country
)