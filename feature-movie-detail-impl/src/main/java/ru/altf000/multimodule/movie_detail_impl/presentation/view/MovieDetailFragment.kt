package ru.altf000.multimodule.movie_detail_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.altf000.multimodule.common.fragment.argument
import ru.altf000.multimodule.common.navigation.CustomRouter
import ru.altf000.multimodule.common.utils.ContentUtils
import ru.altf000.multimodule.common.viewmodel.injectViewModel
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_entities.domain.FullContent
import ru.altf000.multimodule.common_ui.fragment.BaseFragment
import ru.altf000.multimodule.common_ui.utils.load
import ru.altf000.multimodule.movie_detail_impl.databinding.FragmentDetailBinding
import ru.altf000.multimodule.movie_detail_impl.di.MovieDetailComponentHolder
import ru.altf000.multimodule.movie_detail_impl.presentation.view.adapter.RecommendationsListAdapter
import ru.altf000.multimodule.movie_detail_impl.presentation.viewmodel.MovieDetailViewModel
import ru.altf000.multimodule.movie_detail_impl.presentation.viewmodel.MovieDetailViewModelFactory
import javax.inject.Inject

internal class MovieDetailFragment : BaseFragment<FragmentDetailBinding>() {

    @Inject
    lateinit var viewModelFactory: MovieDetailViewModelFactory

    @Inject
    lateinit var router: CustomRouter

    var content: Content by argument()

    private lateinit var viewModel: MovieDetailViewModel

    private val recommendationsAdapter = RecommendationsListAdapter {
        viewModel.onItemClicked(it)
    }

    init {
        MovieDetailComponentHolder.getComponent().inject(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModelFactory.content = content
        viewModel = injectViewModel(viewModelFactory)
        viewModel.router = router
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater).apply {
            recommendations.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                itemAnimator = null
                adapter = recommendationsAdapter
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToViewModels()
    }

    private fun subscribeToViewModels() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.contentInfoFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { content ->
                    setContentInfo(content)
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.recommendationsFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { recommendations ->
                    binding.recommendationsContainer.visibility = if (recommendations.isNotEmpty()) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                    setRecommendations(recommendations)
                }
        }
    }

    private fun setContentInfo(content: FullContent) {
        binding.apply {
            background.load(content.thumbUrl)
            title.text = content.title
            meta.text = ContentUtils.getMeta(content)
            desctiption.text = content.synopsis
        }
    }

    private fun setRecommendations(list: List<Content>) {
        recommendationsAdapter.setData(list)
    }
}