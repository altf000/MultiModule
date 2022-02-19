package ru.altf000.multimodule.movie_detail.presentation.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.altf000.multimodule.common.viewmodel.BaseViewModel
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_entities.domain.FullContent
import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import ru.altf000.multimodule.feature_recommendation_api.domain.GetContentRecommendationsUseCase
import ru.altf000.multimodule.movie_detail.domain.GetContentInfoUseCase
import timber.log.Timber

internal class MovieDetailViewModel(
    private val getContentInfo: GetContentInfoUseCase,
    private val getRecommendations: GetContentRecommendationsUseCase,
    private val content: Content
) : BaseViewModel() {

    private val _contentInfoFlow = MutableStateFlow(FullContent())
    val contentInfoFlow = _contentInfoFlow.asStateFlow()

    private val _recommendationsFlow = MutableStateFlow(listOf(Content(), Content(), Content()))
    val recommendationsFlow = _recommendationsFlow.asStateFlow()

    init {
        loadContent()
    }

    private fun loadContent() {
        launch {
            getContentInfo(GetContentInfoUseCase.Params(content)).collectLatest { result ->
                withContext(dispatchersProvider.main) {
                    when (result) {
                        is RequestResult.Success -> {
                            _contentInfoFlow.value = result.value
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
                            _recommendationsFlow.value = result.value
                        }
                        is RequestResult.Failure<*> -> {
                            _recommendationsFlow.value = emptyList()
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