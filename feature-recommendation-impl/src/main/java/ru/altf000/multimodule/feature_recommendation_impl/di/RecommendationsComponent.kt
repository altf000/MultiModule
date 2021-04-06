package ru.altf000.multimodule.feature_recommendation_impl.di

import dagger.Component
import ru.altf000.multimodule.common.di.ScopeFeature
import ru.altf000.multimodule.feature_recommendation_api.RecommendationsApi

@Component(dependencies = [RecommendationsDependencies::class], modules = [RecommendationsModule::class])
@ScopeFeature
internal abstract class RecommendationsComponent : RecommendationsApi {

}