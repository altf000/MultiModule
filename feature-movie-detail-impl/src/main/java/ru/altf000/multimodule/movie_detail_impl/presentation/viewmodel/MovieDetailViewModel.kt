package ru.altf000.multimodule.movie_detail_impl.presentation.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.altf000.multimodule.common.navigation.CustomRouter
import ru.altf000.multimodule.common.navigation.ScreenKeys
import ru.altf000.multimodule.common.navigation.ScreensChecker
import ru.altf000.multimodule.common.viewmodel.BaseViewModel
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_entities.domain.FullContent
import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import ru.altf000.multimodule.feature_recommendation_api.RecommendationsApi
import ru.altf000.multimodule.movie_detail_impl.di.MovieDetailComponentHolder
import ru.altf000.multimodule.movie_detail_impl.domain.GetContentInfoUseCase
import timber.log.Timber
import javax.inject.Provider

internal class MovieDetailViewModel(
    private val getContentInfoUseCase: GetContentInfoUseCase,
    private val recommendationsApi: Provider<RecommendationsApi>,
    private val content: Content
) : BaseViewModel() {

    private val _contentInfoFlow = MutableStateFlow(FullContent())
    val contentInfoFlow: StateFlow<FullContent> get() = _contentInfoFlow

    private val _recommendationsFlow = MutableStateFlow(listOf(Content(), Content(), Content()))
    val recommendationsFlow: StateFlow<List<Content>> = _recommendationsFlow.asStateFlow()

    var router: CustomRouter? = null

    init {
        loadContent()
    }

    private fun loadContent() {
        content.let { notNullContent ->
            launch {
                getContentInfoUseCase
                    .execute(GetContentInfoUseCase.Params(notNullContent))
                    .collect { result ->
                        withContext(Dispatchers.Main) {
                            when (result) {
                                is RequestResult.Success -> {
                                    _contentInfoFlow.value = result.value
                                    loadRecommendations()
                                    Timber
                                        .tag(MovieDetailViewModel::class.simpleName)
                                        .d("content info: ${result.value}")
                                }
                                is RequestResult.Failure<*> -> {
                                    Timber.e("error to get content info: ${result.error}")
                                }
                            }
                        }
                    }
            }
        }
    }

    private fun loadRecommendations() {
        content.let { notNullContent ->
            launch {
                recommendationsApi
                    .get()
                    .getContentRecommendationsUseCase()
                    .getRecommendations(notNullContent.id)
                    .collect { result ->
                        withContext(Dispatchers.Main) {
                            when (result) {
                                is RequestResult.Success -> {
                                    _recommendationsFlow.value = result.value
                                    Timber
                                        .tag(MovieDetailViewModel::class.simpleName)
                                        .d("recommendations: ${result.value}")
                                }
                                is RequestResult.Failure<*> -> {
                                    _recommendationsFlow.value = emptyList()
                                    Timber.e("error to get recommendations info: ${result.error}")
                                }
                            }
                        }
                    }
            }
        }
    }

    override fun onCleared() {
        if (!ScreensChecker.hasScreen(ScreenKeys.MOVIE_DETAIL)) {
            MovieDetailComponentHolder.reset()
            recommendationsApi.get().getUtils().release()
        }
        super.onCleared()
    }

    fun onItemClicked(content: Content) {
        router?.openMovieDetail(content)
    }
}