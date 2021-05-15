package ru.altf000.multimodule.collection_list_impl.presentation.view.adapter.loading

import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import ru.altf000.multimodule.collection_list.databinding.ItemNetworkStateBinding
import ru.altf000.multimodule.common_ui.utils.isVisible

class CollectionLoadViewHolder(
    private val binding: ItemNetworkStateBinding,
    private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retryCallback() }
    }

    fun bind(loadState: LoadState) {
        with(binding) {
            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            errorMsg.isVisible = !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
            errorMsg.text = (loadState as? LoadState.Error)?.error?.message
        }
    }
}