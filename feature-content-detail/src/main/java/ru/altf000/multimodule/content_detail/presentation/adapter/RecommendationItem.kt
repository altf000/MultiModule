package ru.altf000.multimodule.content_detail.presentation.adapter

import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_ui.adapterdelegates.DItem

data class RecommendationItem(
    private val content: Content,
    val isStub: Boolean = false,
) : DItem() {
    override val identifier: Int = content.hashCode()
    override val data: Content = content
}