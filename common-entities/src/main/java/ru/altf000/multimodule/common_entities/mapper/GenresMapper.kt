package ru.altf000.multimodule.common_entities.mapper

import ru.altf000.multimodule.common_entities.domain.Genre
import ru.altf000.multimodule.common_entities.entity.GenreEntity
import ru.altf000.multimodule.common_entities.network.categories.GenreNetwork

fun GenreNetwork.toDomain() = Genre(this.id, this.title.orEmpty())
fun GenreEntity.toDomain() = Genre(this.id, this.title)
fun GenreNetwork.toEntity() = GenreEntity(this.id, this.title.orEmpty())