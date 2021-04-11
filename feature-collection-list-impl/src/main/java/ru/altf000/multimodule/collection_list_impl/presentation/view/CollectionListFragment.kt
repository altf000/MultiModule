package ru.altf000.multimodule.collection_list_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.altf000.multimodule.collection_list.databinding.FragmentCollectionListBinding
import ru.altf000.multimodule.collection_list_impl.di.CollectionListComponentHolder
import ru.altf000.multimodule.collection_list_impl.presentation.view.adapter.CollectionListAdapter
import ru.altf000.multimodule.collection_list_impl.presentation.viewmodel.CollectionListViewModel
import ru.altf000.multimodule.common.fragment.argument
import ru.altf000.multimodule.common.navigation.CustomRouter
import ru.altf000.multimodule.common.viewmodel.injectViewModel
import ru.altf000.multimodule.common_ui.fragment.BaseFragment
import ru.altf000.multimodule.common_ui.utils.addLoadMoreListener
import javax.inject.Inject

internal class CollectionListFragment : BaseFragment<FragmentCollectionListBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var router: CustomRouter

    var collectionId: Int by argument()

    private lateinit var viewModel: CollectionListViewModel

    private val collectionListAdapter: CollectionListAdapter = CollectionListAdapter {
        viewModel.onItemClicked(it)
    }

    init {
        CollectionListComponentHolder.getComponent().inject(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = injectViewModel<CollectionListViewModel>(viewModelFactory).apply {
            val fragment = this@CollectionListFragment
            collectionId = fragment.collectionId
            router = fragment.router
        }
        subscribeToViewModels()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCollectionListBinding.inflate(layoutInflater).apply {
            list.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = collectionListAdapter
                addLoadMoreListener { viewModel.loadMore() }
            }
            root.setOnRefreshListener { viewModel.refresh() }
        }
        return binding.root
    }

    override fun onStartInner() {
        viewModel.loadFirstPage()
    }

    override fun onStopInner() {}

    private fun subscribeToViewModels() {

        viewModel.collectionList.observe(this, Observer {
            binding.root.isRefreshing = false
            collectionListAdapter.setData(it)
        })

        viewModel.collectionListError.observe(this, Observer {
            if (it) {
                binding.root.isRefreshing = false
                Toast.makeText(context, "Ошибка загрузки данных", Toast.LENGTH_LONG).show()
            }
        })
    }
}
