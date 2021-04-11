package ru.altf000.multimodule.movie_detail_impl.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_ui.diffutil.ContentDiff
import ru.altf000.multimodule.common_ui.utils.load
import ru.altf000.multimodule.movie_detail_impl.R

class RecommendationsListAdapter(private val onClickListener: (Content) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: MutableList<Content> = mutableListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return if (viewType == 0) {
            val view = inflater.inflate(R.layout.item_recommendation_stub, viewGroup, false)
            RecommendationsStubViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_recommendation, viewGroup, false)
            RecommendationsViewHolder(view)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is RecommendationsViewHolder) {
            val item = items[position]
            viewHolder.poster.load(item.posterUrl)
            viewHolder.title.text = item.title
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
        val diffCallback = ContentDiff(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
}