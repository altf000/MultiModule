package ru.altf000.multimodule.feature_collections.presentation.view.adapter

import ru.altf000.multimodule.common_ui.adapterdelegates.DItem

internal data class HorizontalItem(
    private val itemData: HorizontalItemData
) : DItem() {
    override val identifier: Int = itemData.hashCode()
    override val data: HorizontalItemData = itemData
}

internal data class HorizontalItemData(
    val title: String,
    val items: List<MovieItem>
)