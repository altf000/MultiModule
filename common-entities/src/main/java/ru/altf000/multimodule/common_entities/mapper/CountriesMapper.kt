package ru.altf000.multimodule.common_entities.mapper

import ru.altf000.multimodule.common_entities.domain.Country
import ru.altf000.multimodule.common_entities.entity.CountryEntity
import ru.altf000.multimodule.common_entities.network.countries.CountryNetwork

fun CountryEntity.toDomain() = Country(this.id, this.title, this.code)
fun CountryNetwork.toDomain() = Country(this.id, this.title.orEmpty(), this.code.orEmpty())
fun CountryNetwork.toEntity() = CountryEntity(this.id, this.title.orEmpty(), this.code.orEmpty())