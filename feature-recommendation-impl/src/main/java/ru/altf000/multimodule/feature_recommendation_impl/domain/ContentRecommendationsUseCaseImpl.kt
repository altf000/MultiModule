package ru.altf000.multimodule.feature_recommendation_impl.domain

import ru.altf000.multimodule.common.di.ScopeFeature
import ru.altf000.multimodule.feature_recommendation_api.domain.ContentRecommendationsUseCase
import javax.inject.Inject

@ScopeFeature
internal class ContentRecommendationsUseCaseImpl @Inject constructor(
    private val recommendationsRepository: RecommendationsRepository
) : ContentRecommendationsUseCase {

    override fun getRecommendations(contentId: Int) =
        recommendationsRepository.getContentRecommendations(contentId)
}