package ru.altf000.multimodule.feature_collections.presentation.view.adapter

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_ui.adapterdelegates.AdapterDelegate
import ru.altf000.multimodule.common_ui.adapterdelegates.CompositeAdapter
import ru.altf000.multimodule.common_ui.adapterdelegates.delegateSelector
import ru.altf000.multimodule.feature_collections.R
import ru.altf000.multimodule.feature_collections.databinding.ItemHorizontalBinding

internal class HorizontalItemAdapter(
    private val onHeaderClick: (collectionId: Int) -> Unit,
    private val onItemClick: (content: Content) -> Unit
) : AdapterDelegate<HorizontalItem, ItemHorizontalBinding>() {

    override val viewType: Int = R.layout.item_horizontal
    override val itemClass = HorizontalItem::class.java

    private val scrollStates = mutableMapOf<Int, Parcelable?>()

    override fun createBinding(parent: ViewGroup) =
        ItemHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false).apply {
            recyclerView.apply {
                adapter = CompositeAdapter(delegateSelector { addDelegate(ContentItemAdapter(onItemClick)) })
                layoutManager = LinearLayoutManager(parent.context, RecyclerView.HORIZONTAL, false)
            }
        }

    override fun onBind(item: HorizontalItem, binding: ItemHorizontalBinding, position: Int, payloads: List<Any>) {
        binding.title.text = item.data.title
        binding.title.setOnClickListener { onHeaderClick(item.data.collectionId) }
        (binding.recyclerView.adapter as CompositeAdapter).submitList(item.data.items) {
            scrollStates[position]?.let {
                (binding.recyclerView.layoutManager as? LinearLayoutManager)?.onRestoreInstanceState(it)
            }
        }
    }

    override fun onUnbind(binding: ItemHorizontalBinding, position: Int) {
        (binding.recyclerView.layoutManager as? LinearLayoutManager)?.let {
            scrollStates[position] = it.onSaveInstanceState()
        }
        binding.title.text = null
        binding.title.setOnClickListener(null)
        (binding.recyclerView.adapter as CompositeAdapter).submitList(emptyList())
    }
}