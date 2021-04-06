package ru.altf000.multimodule.collection_list_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.altf000.multimodule.collection_list.databinding.FragmentCollectionListBinding
import ru.altf000.multimodule.collection_list_impl.di.CollectionListComponentHolder
import ru.altf000.multimodule.collection_list_impl.presentation.view.adapter.MovieListAdapter
import ru.altf000.multimodule.collection_list_impl.presentation.viewmodel.CollectionListViewModel
import ru.altf000.multimodule.common.fragment.argument
import ru.altf000.multimodule.common.navigation.CustomRouter
import ru.altf000.multimodule.common.viewmodel.injectViewModel
import javax.inject.Inject

internal class CollectionListFragment : Fragment() {

    companion object {
        private const val VISIBLE_THRESHOLD = 1
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var router: CustomRouter

    private lateinit var viewModel: CollectionListViewModel
    private lateinit var binding: FragmentCollectionListBinding

    var collectionId: Int by argument()

    private val moviesAdapter: MovieListAdapter = MovieListAdapter {
        viewModel.onItemClicked(it)
    }

    init {
        CollectionListComponentHolder.getComponent().inject(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = injectViewModel<CollectionListViewModel>(viewModelFactory)
            .apply {
                collectionList.observe(this@CollectionListFragment, Observer {
                    moviesAdapter.setData(it)
                })
                errorList.observe(this@CollectionListFragment, Observer {
                    if (it) {
                        Toast.makeText(context, "Ошибка загрузки данных", Toast.LENGTH_LONG).show()
                    }
                })
                router = this@CollectionListFragment.router
                collectionId = arguments?.getInt("collectionId") ?: -1
                loadFirst()
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCollectionListBinding.inflate(layoutInflater).apply {
            list.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = moviesAdapter
            }
        }
        setupScrollListener()
        return binding.root
    }

    private fun setupScrollListener() {
        val layoutManager = binding.list.layoutManager as LinearLayoutManager
        binding.list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
                    viewModel.loadMore()
                }
            }
        })
    }
}
