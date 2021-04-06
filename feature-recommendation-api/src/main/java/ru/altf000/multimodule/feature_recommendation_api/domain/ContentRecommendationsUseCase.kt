package ru.altf000.multimodule.feature_recommendation_api.domain

import kotlinx.coroutines.flow.Flow
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_network.network.adapter.RequestResult

interface ContentRecommendationsUseCase {

    fun getRecommendations(contentId: Int): Flow<RequestResult<List<Content>>>
}