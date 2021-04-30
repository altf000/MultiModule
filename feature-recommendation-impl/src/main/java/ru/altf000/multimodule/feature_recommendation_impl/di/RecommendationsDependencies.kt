package ru.altf000.multimodule.feature_recommendation_impl.di

import ru.altf000.multimodule.common_db.db.AppDatabase
import ru.altf000.multimodule.common_network.network.api.ApiService
import ru.altf000.multimodule.module_injector.BaseDependencies

interface RecommendationsDependencies : BaseDependencies {

    fun getApiService(): ApiService

    fun getDatabase(): AppDatabase
}