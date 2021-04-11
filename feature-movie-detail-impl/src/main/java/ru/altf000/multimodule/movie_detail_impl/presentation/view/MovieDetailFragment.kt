package ru.altf000.multimodule.movie_detail_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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
import javax.inject.Inject

internal class MovieDetailFragment : BaseFragment<FragmentDetailBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var router: CustomRouter

    var content: Content by argument()

    private lateinit var viewModel: MovieDetailViewModel

    private val recommendationsAdapter: RecommendationsListAdapter = RecommendationsListAdapter {
        viewModel.onItemClicked(it)
    }

    init {
        MovieDetailComponentHolder.getComponent().inject(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = injectViewModel<MovieDetailViewModel>(viewModelFactory).apply {
            val fragment = this@MovieDetailFragment
            content = fragment.content
            router = fragment.router
        }
        subscribeToViewModels()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater).apply {
            recommendations.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = recommendationsAdapter
            }
        }
        return binding.root
    }

    override fun onStartInner() {
        viewModel.loadContent()
    }

    override fun onStopInner() {}

    private fun subscribeToViewModels() {

        viewModel.contentInfo.observe(this, Observer {
            setContentInfo(it)
        })

        viewModel.recommendations.observe(this, Observer {
            binding.recommendationsContainer.visibility = if (it.isNotEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
            setRecommendations(it)
        })
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