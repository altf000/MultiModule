package ru.altf000.multimodule.feature_recommendation_api

import ru.altf000.multimodule.feature_recommendation_api.domain.ContentRecommendationsUseCase
import ru.altf000.multimodule.module_injector.BaseAPI

interface RecommendationsApi : BaseAPI {
    fun getContentRecommendationsUseCase(): ContentRecommendationsUseCase
    fun getReleaseManager(): ReleaseManager
}