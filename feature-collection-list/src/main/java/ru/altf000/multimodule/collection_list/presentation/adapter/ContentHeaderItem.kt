package ru.altf000.multimodule.collection_list.presentation.adapter

import ru.altf000.multimodule.common_ui.adapterdelegates.DItem

internal data class ContentHeaderItem(private val text: String) : DItem() {
    override val identifier: Int = text.hashCode()
    override val data: String = text
}