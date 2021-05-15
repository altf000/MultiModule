package ru.altf000.multimodule.collection_list_impl.presentation.view.adapter.loading

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.altf000.multimodule.collection_list.R
import ru.altf000.multimodule.collection_list.databinding.ItemNetworkStateBinding

class CollectionLoadStateAdapter<T : Any, VH : RecyclerView.ViewHolder>(
    private val adapter: PagingDataAdapter<T, VH>
) : LoadStateAdapter<CollectionLoadViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CollectionLoadViewHolder {
        return CollectionLoadViewHolder(
            ItemNetworkStateBinding.bind(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_network_state, parent, false)
            )
        ) { adapter.retry() }
    }

    override fun onBindViewHolder(holder: CollectionLoadViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}
