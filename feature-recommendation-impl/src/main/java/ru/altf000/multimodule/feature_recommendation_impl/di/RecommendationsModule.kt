package ru.altf000.multimodule.feature_recommendation_impl.di

import dagger.Binds
import dagger.Module
import ru.altf000.multimodule.common.di.ScopeFeature
import ru.altf000.multimodule.feature_recommendation_api.ReleaseManager
import ru.altf000.multimodule.feature_recommendation_api.domain.ContentRecommendationsUseCase
import ru.altf000.multimodule.feature_recommendation_impl.ReleaseManagerImpl
import ru.altf000.multimodule.feature_recommendation_impl.data.RecommendationsRepositoryImpl
import ru.altf000.multimodule.feature_recommendation_impl.domain.ContentRecommendationsUseCaseImpl
import ru.altf000.multimodule.feature_recommendation_impl.domain.RecommendationsRepository

@Module
internal abstract class RecommendationsModule {

    @ScopeFeature
    @Binds
    abstract fun provideRepository(repository: RecommendationsRepositoryImpl): RecommendationsRepository

    @ScopeFeature
    @Binds
    abstract fun provideUseCase(useCase: ContentRecommendationsUseCaseImpl): ContentRecommendationsUseCase

    @ScopeFeature
    @Binds
    abstract fun provideUtils(utils: ReleaseManagerImpl): ReleaseManager
}