package ru.altf000.multimodule.feature_collection_list.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.altf000.multimodule.feature_collection_list.R
import ru.altf000.multimodule.feature_collection_list.databinding.ItemContentHeaderBinding
import ru.altf000.multimodule.common_ui.adapterdelegates.AdapterDelegate

internal class ContentHeaderItemAdapter :
    AdapterDelegate<ContentHeaderItem, ItemContentHeaderBinding>() {

    override val viewType = R.layout.item_content_header
    override val itemClass = ContentHeaderItem::class.java

    override fun createBinding(parent: ViewGroup) =
        ItemContentHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun onBind(
        item: ContentHeaderItem,
        binding: ItemContentHeaderBinding,
        position: Int,
        payloads: List<Any>,
    ) {
        binding.title.text = item.data
    }

    override fun onUnbind(binding: ItemContentHeaderBinding, position: Int) {
        binding.title.text = null
    }
}