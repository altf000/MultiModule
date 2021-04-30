package ru.altf000.multimodule.common_entities.mapper

import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_entities.entity.RecommendationEntity

fun Content.toRecommendationEntity(parentId: Int) = RecommendationEntity(
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

fun RecommendationEntity.toContent() = Content(
    id = this.id,
    title = this.title,
    synopsis = this.synopsis,
    posterUrl = this.posterUrl,
    rating = this.rating,
    year = this.year,
    restrict = this.restrict,
    isSerial = this.isSerial
)