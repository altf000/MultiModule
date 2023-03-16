package ru.altf000.multimodule.feature_collection_list.presentation.view

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.altf000.multimodule.feature_collection_list.R
import ru.altf000.multimodule.feature_collection_list.databinding.FragmentCollectionListBinding
import ru.altf000.multimodule.feature_collection_list.presentation.adapter.ContentHeaderItemAdapter
import ru.altf000.multimodule.feature_collection_list.presentation.adapter.ContentItemAdapter
import ru.altf000.multimodule.feature_collection_list.presentation.viewmodel.CollectionListViewModel
import ru.altf000.multimodule.common_ui.adapterdelegates.CompositePagingAdapter
import ru.altf000.multimodule.common_ui.adapterdelegates.createAdapter
import ru.altf000.multimodule.common_ui.adapterdelegates.delegateSelector
import ru.altf000.multimodule.common_ui.fragment.BaseFragment
import ru.altf000.multimodule.common_ui.utils.viewBinding
import ru.altf000.multimodule.common_utils.extentions.collectLatestOnCreated

internal class CollectionListFragment : BaseFragment(R.layout.fragment_collection_list) {

    private val args by navArgs<CollectionListFragmentArgs>()
    private val viewModel: CollectionListViewModel by viewModel { parametersOf(args.collectionId) }

    private lateinit var pagingAdapter: CompositePagingAdapter

    override val binding by viewBinding(FragmentCollectionListBinding::bind)

    override fun onBind(savedInstanceState: Bundle?) = binding.apply {
        recyclerView.adapter = createAdapter(
            lifecycleOwner = viewLifecycleOwner,
            recyclerView = recyclerView,
            selector = delegateSelector {
                addDelegate(ContentItemAdapter { viewModel.onContentClicked(it) })
                addDelegate(ContentHeaderItemAdapter())
            }
        ) {
            pagingAdapter = addPagingAdapter(viewModel.pager).withNetworkStateAdapter()
        }
        pagingAdapter.loadStateFlow.collectLatestOnCreated(viewLifecycleOwner) { loadState ->
            binding.root.isRefreshing = loadState.refresh is LoadState.Loading
        }
        root.setOnRefreshListener { pagingAdapter.refresh() }
    }
}