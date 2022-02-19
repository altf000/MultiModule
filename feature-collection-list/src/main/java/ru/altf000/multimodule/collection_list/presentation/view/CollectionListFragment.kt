package ru.altf000.multimodule.collection_list.presentation.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.altf000.multimodule.collection_list.R
import ru.altf000.multimodule.collection_list.databinding.FragmentCollectionListBinding
import ru.altf000.multimodule.collection_list.presentation.view.adapter.CollectionListAdapter
import ru.altf000.multimodule.collection_list.presentation.view.adapter.loading.CollectionLoadStateAdapter
import ru.altf000.multimodule.collection_list.presentation.viewmodel.CollectionListViewModel
import ru.altf000.multimodule.common_ui.fragment.BaseFragment
import ru.altf000.multimodule.common_ui.utils.viewBinding
import ru.altf000.multimodule.common_utils.extentions.collectLatestOnCreated

internal class CollectionListFragment : BaseFragment(R.layout.fragment_collection_list) {

    private val args by navArgs<CollectionListFragmentArgs>()
    private val viewModel: CollectionListViewModel by viewModel { parametersOf(args.collectionId) }
    private val collectionListAdapter = CollectionListAdapter { viewModel.onItemClicked(it) }

    override val binding by viewBinding(FragmentCollectionListBinding::bind)

    override fun onBind(savedInstanceState: Bundle?) = binding.apply {
        list.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = collectionListAdapter.withLoadStateHeaderAndFooter(
                header = CollectionLoadStateAdapter(collectionListAdapter),
                footer = CollectionLoadStateAdapter(collectionListAdapter)
            )
        }
        root.setOnRefreshListener { collectionListAdapter.refresh() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.collectionListFlow.collectLatestOnCreated(viewLifecycleOwner) { pagingData ->
            collectionListAdapter.submitData(pagingData)
        }
        collectionListAdapter.loadStateFlow.collectLatestOnCreated(viewLifecycleOwner) { loadState ->
            binding.root.isRefreshing = loadState.refresh is LoadState.Loading
        }
    }
}