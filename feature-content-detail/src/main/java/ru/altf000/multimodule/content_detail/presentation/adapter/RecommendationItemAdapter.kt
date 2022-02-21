package ru.altf000.multimodule.content_detail.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_ui.adapterdelegates.AdapterDelegate
import ru.altf000.multimodule.common_ui.utils.load
import ru.altf000.multimodule.content_detail.R
import ru.altf000.multimodule.content_detail.databinding.ItemRecommendationBinding

internal class RecommendationItemAdapter(
    private val onContentClickAction: (Content) -> Unit
) : AdapterDelegate<RecommendationItem, ItemRecommendationBinding>() {

    override val viewType: Int = R.layout.item_recommendation
    override val itemClass = RecommendationItem::class.java

    override fun createBinding(parent: ViewGroup) =
        ItemRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun onBind(item: RecommendationItem, binding: ItemRecommendationBinding, position: Int, payloads: List<Any>) {
        with(binding) {
            poster.load(item.data.posterUrl)
            title.text = item.data.title
            root.setOnClickListener { onContentClickAction(item.data) }
        }
    }

    override fun onUnbind(binding: ItemRecommendationBinding, position: Int) {
        with(binding) {
            poster.setImageDrawable(null)
            title.text = null
            root.setOnClickListener(null)
        }
    }
}