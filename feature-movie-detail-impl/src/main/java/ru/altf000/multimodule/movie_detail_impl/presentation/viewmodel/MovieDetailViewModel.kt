package ru.altf000.multimodule.movie_detail_impl.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
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
    private val recommendationsApi: Provider<RecommendationsApi>
) : BaseViewModel() {

    private val _contentInfo = MutableLiveData<FullContent>()
    val contentInfo: LiveData<FullContent> get() = _contentInfo

    private val _recommendations = MutableLiveData<List<Content>>()
    val recommendations: LiveData<List<Content>> get() = _recommendations

    var router: CustomRouter? = null
    var content: Content? = null

    fun loadContent() {
        content?.let { notNullContent ->
            launch {
                getContentInfoUseCase
                    .execute(GetContentInfoUseCase.Params(notNullContent))
                    .collect { result ->
                        withContext(Dispatchers.Main) {
                            when (result) {
                                is RequestResult.Success -> {
                                    _contentInfo.value = result.value
                                    loadRecommendations()
                                    Timber.tag(MovieDetailViewModel::class.simpleName).d("content info: ${result.value}")
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
        content?.let { notNullContent ->
            launch {
                recommendationsApi
                    .get()
                    .getContentRecommendationsUseCase()
                    .getRecommendations(notNullContent.id)
                    .collect { result ->
                        withContext(Dispatchers.Main) {
                            if (result is RequestResult.Success) {
                                _recommendations.value = result.value
                                Timber.tag(MovieDetailViewModel::class.simpleName).d("recommendations: ${result.value}")
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
}