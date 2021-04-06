package ru.altf000.multimodule.movie_detail_impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.altf000.multimodule.feature_recommendation_api.RecommendationsApi
import ru.altf000.multimodule.feature_recommendation_api.domain.ContentRecommendationsUseCase
import ru.altf000.multimodule.movie_detail_impl.domain.GetContentInfoUseCase
import javax.inject.Inject
import javax.inject.Provider

internal class MovieDetailViewModelFactory @Inject constructor(
    private val getContentInfoUseCase: GetContentInfoUseCase,
    private val recommendationsApi: Provider<RecommendationsApi>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(getContentInfoUseCase, recommendationsApi) as T
    }
}