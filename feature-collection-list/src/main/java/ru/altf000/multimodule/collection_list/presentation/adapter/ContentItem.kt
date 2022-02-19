package ru.altf000.multimodule.collection_list.presentation.adapter

import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_ui.adapterdelegates.DItem

data class ContentItem(private val content: Content) : DItem() {
    override val identifier: Int = content.hashCode()
    override val data: Content = content
}