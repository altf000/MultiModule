package ru.altf000.multimodule.movie_detail_impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.feature_recommendation_api.RecommendationsApi
import ru.altf000.multimodule.movie_detail_impl.domain.GetContentInfoUseCase
import javax.inject.Provider

internal class MovieDetailViewModelFactory @AssistedInject constructor(
    @Assisted private val content: Content,
    private val getContentInfoUseCase: GetContentInfoUseCase,
    private val recommendationsApi: Provider<RecommendationsApi>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MovieDetailViewModel(getContentInfoUseCase, recommendationsApi, content) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted content: Content): MovieDetailViewModelFactory
    }
}