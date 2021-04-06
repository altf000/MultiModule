package ru.altf000.multimodule.movie_detail_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.altf000.multimodule.common.fragment.argument
import ru.altf000.multimodule.common.navigation.CustomRouter
import ru.altf000.multimodule.common.utils.ContentUtils
import ru.altf000.multimodule.common.utils.load
import ru.altf000.multimodule.common.viewmodel.injectViewModel
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_entities.domain.FullContent
import ru.altf000.multimodule.movie_detail_impl.databinding.FragmentDetailBinding
import ru.altf000.multimodule.movie_detail_impl.di.MovieDetailComponentHolder
import ru.altf000.multimodule.movie_detail_impl.presentation.viewmodel.MovieDetailViewModel
import javax.inject.Inject

internal class MovieDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var router: CustomRouter

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MovieDetailViewModel

    var content: Content by argument()

    init {
        MovieDetailComponentHolder.getComponent().inject(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = injectViewModel<MovieDetailViewModel>(viewModelFactory)
            .apply {
                val fragment = this@MovieDetailFragment
                content = fragment.content
                router = fragment.router
                contentInfo.observe(fragment, Observer {
                    setUi(it)
                })
                recommendations.observe(fragment, Observer {
                    setRecommendations(it)
                })
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadContent()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setUi(content: FullContent) {
        binding.background.load(content.thumbUrl)
        binding.title.text = content.title
        binding.meta.text = ContentUtils.getMeta(content)
        binding.desctiption.text = content.synopsis
    }

    private fun setRecommendations(list: List<Content>) {

    }
}