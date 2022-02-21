package ru.altf000.multimodule.feature_collections.presentation.view

import android.os.Bundle
import androidx.paging.LoadState
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.altf000.multimodule.common_ui.adapterdelegates.CompositePagingAdapter
import ru.altf000.multimodule.common_ui.adapterdelegates.createAdapter
import ru.altf000.multimodule.common_ui.adapterdelegates.delegateSelector
import ru.altf000.multimodule.common_ui.fragment.BaseFragment
import ru.altf000.multimodule.common_ui.utils.viewBinding
import ru.altf000.multimodule.common_utils.extentions.collectLatestOnCreated
import ru.altf000.multimodule.feature_collections.R
import ru.altf000.multimodule.feature_collections.databinding.FragmentCollectionsBinding
import ru.altf000.multimodule.feature_collections.presentation.view.adapter.HorizontalItemAdapter
import ru.altf000.multimodule.feature_collections.presentation.viewmodel.CollectionsViewModel

internal class CollectionsFragment : BaseFragment(R.layout.fragment_collections) {

    private val viewModel: CollectionsViewModel by viewModel()
    private lateinit var pagingAdapter: CompositePagingAdapter

    override val binding by viewBinding(FragmentCollectionsBinding::bind)

    override fun onBind(savedInstanceState: Bundle?) = binding.apply {
        recyclerView.adapter = createAdapter(
            lifecycleOwner = viewLifecycleOwner,
            recyclerView = recyclerView,
            selector = delegateSelector {
                addDelegate(HorizontalItemAdapter({ viewModel.onHeaderClicked(it) }) { viewModel.onContentClicked(it) })
            }
        ) {
            pagingAdapter = addPagingAdapter(viewModel.collectionItems).withNetworkStateAdapter()
        }
        pagingAdapter.loadStateFlow.collectLatestOnCreated(viewLifecycleOwner) { loadState ->
            binding.root.isRefreshing = loadState.refresh is LoadState.Loading
        }
        root.setOnRefreshListener { pagingAdapter.refresh() }
    }
}