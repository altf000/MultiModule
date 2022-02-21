package ru.altf000.multimodule.content_detail.presentation.adapter

import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_ui.adapterdelegates.AdapterDelegatesSelector

internal class RecommendationItemAdapterSelector(
    private val onContentClickAction: (Content) -> Unit
) : AdapterDelegatesSelector<RecommendationItem>() {

    override val itemClass = RecommendationItem::class.java

    override fun getDelegate(item: RecommendationItem) = when {
        item.isStub -> RecommendationItemAdapterStub()
        else -> RecommendationItemAdapter(onContentClickAction)
    }
}