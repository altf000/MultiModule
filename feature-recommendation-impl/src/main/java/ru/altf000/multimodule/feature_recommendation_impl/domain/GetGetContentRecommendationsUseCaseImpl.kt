package ru.altf000.multimodule.feature_recommendation_impl.domain

import ru.altf000.multimodule.feature_recommendation_api.domain.GetContentRecommendationsUseCase

internal class GetGetContentRecommendationsUseCaseImpl(
    private val recommendationsRepository: RecommendationsRepository
) : GetContentRecommendationsUseCase {

    override fun invoke(contentId: Int) =
        recommendationsRepository.getContentRecommendations(contentId)
}