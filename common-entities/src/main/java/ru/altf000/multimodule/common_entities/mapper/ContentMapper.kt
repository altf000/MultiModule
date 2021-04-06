package ru.altf000.multimodule.common_entities.mapper

import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_entities.server.content.ContentResponse
import ru.altf000.multimodule.common_entities.server.content.Kind

fun ContentResponse.toLocal() = Content(
    id = this.id,
    title = this.title.orEmpty(),
    synopsis = this.synopsis.orEmpty(),
    posterUrl = this.posters.first().url.orEmpty(),
    rating = this.rating?.ready?.main.toString(),
    year = this.year,
    restrict = this.restrict,
    isSerial = this.kind == Kind.SERIAL
)