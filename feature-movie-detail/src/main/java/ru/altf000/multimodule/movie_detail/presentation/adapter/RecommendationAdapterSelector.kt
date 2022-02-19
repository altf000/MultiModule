package ru.altf000.multimodule.movie_detail.presentation.adapter

import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_ui.adapterdelegates.AdapterDelegatesSelector

internal class RecommendationAdapterSelector(
    private val onClickAction: (Content) -> Unit
) : AdapterDelegatesSelector<RecommendationItem>() {

    override val itemClass = RecommendationItem::class.java

    override fun getDelegate(item: RecommendationItem) = when {
        item.isStub -> RecommendationAdapterStub()
        else -> RecommendationAdapter(onClickAction)
    }
}