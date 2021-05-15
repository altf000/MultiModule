package ru.altf000.multimodule.feature_recommendation_impl.di

import ru.altf000.multimodule.feature_recommendation_api.RecommendationsApi
import ru.altf000.multimodule.module_injector.ComponentHolder

object RecommendationsComponentHolder :
    ComponentHolder<RecommendationsApi, RecommendationsDependencies> {

    private var component: RecommendationsComponent? = null

    override fun init(dependencies: RecommendationsDependencies) {
        if (component == null) {
            synchronized(RecommendationsComponentHolder::class.java) {
                if (component == null) {
                    component = DaggerRecommendationsComponent
                        .builder()
                        .recommendationsDependencies(dependencies)
                        .build()
                }
            }
        }
    }

    override fun get(): RecommendationsApi {
        return component!!
    }

    override fun reset() {
        component = null
    }
}