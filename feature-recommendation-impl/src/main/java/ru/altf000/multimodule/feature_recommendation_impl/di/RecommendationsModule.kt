package ru.altf000.multimodule.feature_recommendation_impl.di

import dagger.Module
import dagger.Provides
import ru.altf000.multimodule.common.di.ScopeFeature
import ru.altf000.multimodule.feature_recommendation_api.Utils
import ru.altf000.multimodule.feature_recommendation_api.domain.ContentRecommendationsUseCase
import ru.altf000.multimodule.feature_recommendation_impl.UtilsImpl
import ru.altf000.multimodule.feature_recommendation_impl.data.RecommendationsRepositoryImpl
import ru.altf000.multimodule.feature_recommendation_impl.domain.ContentRecommendationsUseCaseImpl
import ru.altf000.multimodule.feature_recommendation_impl.domain.RecommendationsRepository

@Module
internal class RecommendationsModule {

    @ScopeFeature
    @Provides
    fun provideRepository(recommendationRepository: RecommendationsRepositoryImpl): RecommendationsRepository {
        return recommendationRepository
    }

    @ScopeFeature
    @Provides
    fun provideUseCase(contentRecommendationUseCase: ContentRecommendationsUseCaseImpl): ContentRecommendationsUseCase {
        return contentRecommendationUseCase
    }

    @ScopeFeature
    @Provides
    fun provideUtils(utils: UtilsImpl): Utils {
        return utils
    }
}