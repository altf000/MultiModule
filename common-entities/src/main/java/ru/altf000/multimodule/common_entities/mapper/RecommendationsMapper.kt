package ru.altf000.multimodule.common_entities.mapper

import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_entities.entity.RecommendationEntity

fun Content.toEntity(parentId: Int) = RecommendationEntity(
    parentId = parentId,
    contentId = this.id,
    title = this.title,
    synopsis = this.synopsis,
    posterUrl = this.posterUrl,
    rating = this.rating,
    year = this.year,
    restrict = this.restrict,
    isSerial = this.isSerial,
)

fun RecommendationEntity.toDomain() = Content(
    id = this.contentId,
    title = this.title,
    synopsis = this.synopsis,
    posterUrl = this.posterUrl,
    rating = this.rating,
    year = this.year,
    restrict = this.restrict,
    isSerial = this.isSerial
)