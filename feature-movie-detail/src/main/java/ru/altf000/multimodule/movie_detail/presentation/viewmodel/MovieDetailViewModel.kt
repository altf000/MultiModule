package ru.altf000.multimodule.movie_detail.presentation.viewmodel

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.altf000.multimodule.common.viewmodel.BaseViewModel
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_entities.domain.FullContent
import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import ru.altf000.multimodule.feature_recommendation_api.domain.GetContentRecommendationsUseCase
import ru.altf000.multimodule.movie_detail.domain.GetContentInfoUseCase
import ru.altf000.multimodule.movie_detail.presentation.adapter.RecommendationItem
import timber.log.Timber

internal class MovieDetailViewModel(
    private val getContentInfo: GetContentInfoUseCase,
    private val getRecommendations: GetContentRecommendationsUseCase,
    private val content: Content
) : BaseViewModel() {

    private val _contentInfo = MutableStateFlow(FullContent())
    val contentInfo = _contentInfo.asStateFlow()

    private val recommendations = MutableStateFlow(listOf(Content(), Content(), Content()))
    val recommendationsItems = recommendations.map { list ->
        list.map { content ->
            if (content.id == -1) {
                RecommendationItem(content = content, isStub = true)
            } else {
                RecommendationItem(content = content)
            }
        }
    }

    init {
        loadContent()
    }

    private fun loadContent() {
        launch {
            getContentInfo(GetContentInfoUseCase.Params(content)).collectLatest { result ->
                withContext(dispatchersProvider.main) {
                    when (result) {
                        is RequestResult.Success -> {
                            _contentInfo.value = result.value
                            loadRecommendations()
                        }
                        is RequestResult.Failure<*> -> {
                            Timber.e("Error to get content info: ${result.error}")
                        }
                    }
                }
            }
        }
    }

    private fun loadRecommendations() {
        launch {
            getRecommendations(content.id).collect { result ->
                withContext(dispatchersProvider.main) {
                    when (result) {
                        is RequestResult.Success -> {
                            recommendations.value = result.value
                        }
                        is RequestResult.Failure<*> -> {
                            recommendations.value = emptyList()
                        }
                    }
                }
            }
        }
    }

    fun onItemClicked(content: Content) {
        navigator.movieDetail(content)
    }
}