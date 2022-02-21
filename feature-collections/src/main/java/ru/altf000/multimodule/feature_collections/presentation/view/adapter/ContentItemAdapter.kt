package ru.altf000.multimodule.feature_collections.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_ui.adapterdelegates.AdapterDelegate
import ru.altf000.multimodule.common_ui.utils.load
import ru.altf000.multimodule.feature_collections.R
import ru.altf000.multimodule.feature_collections.databinding.ItemMovieBinding

internal class ContentItemAdapter(
    private val onContentClickAction: (Content) -> Unit
) : AdapterDelegate<ContentItem, ItemMovieBinding>() {

    override val viewType: Int = R.layout.item_movie
    override val itemClass = ContentItem::class.java

    override fun createBinding(parent: ViewGroup) =
        ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun onBind(item: ContentItem, binding: ItemMovieBinding, position: Int, payloads: List<Any>) {
        with(binding) {
            poster.load(item.data.posterUrl)
            title.text = item.data.title
            root.setOnClickListener { onContentClickAction(item.data) }
        }
    }

    override fun onUnbind(binding: ItemMovieBinding, position: Int) {
        with(binding) {
            poster.setImageDrawable(null)
            title.text = null
            root.setOnClickListener(null)
        }
    }
}