package ru.altf000.multimodule.common_entities.domain

data class Block(
    val id: Int,
    val title: String,
    val collection: List<Content>,
)