package ru.altf000.multimodule.feature_recommendation_impl.di

import org.koin.dsl.module
import ru.altf000.multimodule.feature_recommendation_api.domain.GetContentRecommendationsUseCase
import ru.altf000.multimodule.feature_recommendation_impl.data.RecommendationsRepositoryImpl
import ru.altf000.multimodule.feature_recommendation_impl.domain.GetGetContentRecommendationsUseCaseImpl
import ru.altf000.multimodule.feature_recommendation_impl.domain.RecommendationsRepository

val recommendationsModule = module {
    single<RecommendationsRepository> { RecommendationsRepositoryImpl(get(), get()) }
    factory<GetContentRecommendationsUseCase> { GetGetContentRecommendationsUseCaseImpl(get()) }
}