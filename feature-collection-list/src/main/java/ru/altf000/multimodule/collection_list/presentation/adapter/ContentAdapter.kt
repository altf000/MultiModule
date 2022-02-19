package ru.altf000.multimodule.collection_list.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.altf000.multimodule.collection_list.R
import ru.altf000.multimodule.collection_list.databinding.ItemContentBinding
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_ui.adapterdelegates.AdapterDelegate
import ru.altf000.multimodule.common_ui.utils.load

class ContentAdapter(
    private val onClickAction: (Content) -> Unit
) : AdapterDelegate<ContentItem, ItemContentBinding>() {

    override val viewType: Int = R.layout.item_content
    override val itemClass = ContentItem::class.java

    override fun createBinding(parent: ViewGroup) =
        ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun onBind(item: ContentItem, binding: ItemContentBinding, position: Int, payloads: List<Any>) {
        with(binding) {
            poster.load(item.data.posterUrl)
            title.text = item.data.title
            description.text = item.data.synopsis
            root.setOnClickListener { onClickAction(item.data) }
        }
    }

    override fun onUnbind(binding: ItemContentBinding) {
        with(binding) {
            poster.setImageDrawable(null)
            title.text = null
            description.text = null
            root.setOnClickListener(null)
        }
    }
}