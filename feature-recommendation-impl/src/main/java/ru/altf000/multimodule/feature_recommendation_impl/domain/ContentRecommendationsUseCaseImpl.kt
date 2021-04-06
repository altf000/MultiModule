package ru.altf000.multimodule.feature_recommendation_impl.domain

import kotlinx.coroutines.flow.Flow
import ru.altf000.multimodule.common.di.ScopeFeature
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import ru.altf000.multimodule.feature_recommendation_api.domain.ContentRecommendationsUseCase
import javax.inject.Inject

@ScopeFeature
internal class ContentRecommendationsUseCaseImpl @Inject constructor(
    private val recommendationsRepository: RecommendationsRepository
) : ContentRecommendationsUseCase {

    override fun getRecommendations(contentId: Int): Flow<RequestResult<List<Content>>> {
        return recommendationsRepository.getContentRecommendations(contentId)
    }
}