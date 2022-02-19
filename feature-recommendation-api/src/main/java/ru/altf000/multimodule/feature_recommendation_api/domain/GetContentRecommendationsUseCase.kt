package ru.altf000.multimodule.feature_recommendation_api.domain

import kotlinx.coroutines.flow.Flow
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_network.network.adapter.RequestResult

interface GetContentRecommendationsUseCase {
    operator fun invoke(contentId: Int): Flow<RequestResult<List<Content>>>
}