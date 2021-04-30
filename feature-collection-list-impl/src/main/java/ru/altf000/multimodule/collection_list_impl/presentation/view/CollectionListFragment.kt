package ru.altf000.multimodule.collection_list_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.altf000.multimodule.collection_list.databinding.FragmentCollectionListBinding
import ru.altf000.multimodule.collection_list_impl.di.CollectionListComponentHolder
import ru.altf000.multimodule.collection_list_impl.presentation.view.adapter.CollectionListAdapter
import ru.altf000.multimodule.collection_list_impl.presentation.view.adapter.loading.CollectionLoadStateAdapter
import ru.altf000.multimodule.collection_list_impl.presentation.viewmodel.CollectionListViewModel
import ru.altf000.multimodule.collection_list_impl.presentation.viewmodel.CollectionListViewModelFactory
import ru.altf000.multimodule.common.fragment.argument
import ru.altf000.multimodule.common.navigation.CustomRouter
import ru.altf000.multimodule.common.viewmodel.injectViewModel
import ru.altf000.multimodule.common_ui.fragment.BaseFragment
import javax.inject.Inject

internal class CollectionListFragment : BaseFragment<FragmentCollectionListBinding>() {

    @Inject
    lateinit var viewModelFactory: CollectionListViewModelFactory

    @Inject
    lateinit var router: CustomRouter

    var collectionId: Int by argument()

    private lateinit var viewModel: CollectionListViewModel

    private val collectionListAdapter = CollectionListAdapter {
        viewModel.onItemClicked(it)
    }

    init {
        CollectionListComponentHolder.getComponent().inject(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModelFactory.collectionId = collectionId
        viewModel = injectViewModel(viewModelFactory)
        viewModel.router = router
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCollectionListBinding.inflate(layoutInflater).apply {
            list.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = collectionListAdapter.withLoadStateHeaderAndFooter(
                    header = CollectionLoadStateAdapter(collectionListAdapter),
                    footer = CollectionLoadStateAdapter(collectionListAdapter)
                )
            }
            root.setOnRefreshListener { collectionListAdapter.refresh() }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToViewModels()
    }

    private fun subscribeToViewModels() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.collectionListFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.CREATED)
                .collectLatest { pagingData ->
                    collectionListAdapter.submitData(pagingData)
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            collectionListAdapter.loadStateFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.CREATED)
                .collectLatest { loadState ->
                    binding.root.isRefreshing = loadState.refresh is LoadState.Loading
                }
        }
    }
}