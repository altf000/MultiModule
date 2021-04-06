package ru.altf000.multimodule.movie_detail_impl.di

import ru.altf000.multimodule.common.navigation.CustomRouter
import ru.altf000.multimodule.common_db.db.AppDatabase
import ru.altf000.multimodule.common_network.network.api.ApiService
import ru.altf000.multimodule.feature_recommendation_api.RecommendationsApi
import ru.altf000.multimodule.feature_recommendation_api.domain.ContentRecommendationsUseCase
import ru.altf000.multimodule.module_injector.BaseDependencies

interface MovieDetailDependencies : BaseDependencies {

    fun getApiService(): ApiService

    fun getDatabase(): AppDatabase

    fun getRouter(): CustomRouter

    fun getRecommendationsApi(): RecommendationsApi
}