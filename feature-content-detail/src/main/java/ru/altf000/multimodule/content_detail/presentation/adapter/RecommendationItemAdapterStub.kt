package ru.altf000.multimodule.content_detail.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.altf000.multimodule.common_ui.adapterdelegates.AdapterDelegate
import ru.altf000.multimodule.content_detail.R
import ru.altf000.multimodule.content_detail.databinding.ItemRecommendationStubBinding

internal class RecommendationItemAdapterStub : AdapterDelegate<RecommendationItem, ItemRecommendationStubBinding>() {

    override val viewType: Int = R.layout.item_recommendation_stub
    override val itemClass = RecommendationItem::class.java

    override fun createBinding(parent: ViewGroup) =
        ItemRecommendationStubBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun onBind(item: RecommendationItem, binding: ItemRecommendationStubBinding, position: Int, payloads: List<Any>) {
    }

    override fun onUnbind(binding: ItemRecommendationStubBinding, position: Int) {
    }
}