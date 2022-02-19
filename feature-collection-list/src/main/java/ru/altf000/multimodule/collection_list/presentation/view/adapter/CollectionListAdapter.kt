package ru.altf000.multimodule.collection_list.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.altf000.multimodule.collection_list.R
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_ui.utils.load

class CollectionListAdapter(private val onClickListener: (Content) -> Unit) :
    PagingDataAdapter<Content, RecyclerView.ViewHolder>(ContentComparator()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val view = inflater.inflate(R.layout.item_movie, viewGroup, false)
        return CollectionViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            viewHolder as CollectionViewHolder
            viewHolder.poster.load(item.posterUrl)
            viewHolder.title.text = item.title
            viewHolder.description.text = item.synopsis
            viewHolder.itemView.setOnClickListener { onClickListener(item) }
        }
    }

    class ContentComparator : DiffUtil.ItemCallback<Content>() {

        override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
            return oldItem == newItem
        }
    }
}