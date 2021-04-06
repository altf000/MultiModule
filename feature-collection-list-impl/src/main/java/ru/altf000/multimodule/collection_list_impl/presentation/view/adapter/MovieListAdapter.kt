package ru.altf000.multimodule.collection_list_impl.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.altf000.multimodule.collection_list.R
import ru.altf000.multimodule.common.utils.load
import ru.altf000.multimodule.common_entities.domain.Content

class MovieListAdapter(private val onClickListener: (Content) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: MutableList<Content> = mutableListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return if (viewType == 0) {
            val view = inflater.inflate(R.layout.item_movie_stub, viewGroup, false)
            MovieStubViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_movie, viewGroup, false)
            MovieViewHolder(view)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is MovieViewHolder) {
            val item = items[position]
            viewHolder.poster.load(item.posterUrl)
            viewHolder.title.text = item.title
            viewHolder.description.text = item.synopsis
            viewHolder.itemView.setOnClickListener { onClickListener(item) }
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return if (item.id == -1) {
            0
        } else {
            1
        }
    }

    fun setData(newItems: List<Content>) {
        val diffCallback = MovieListDiffCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
}