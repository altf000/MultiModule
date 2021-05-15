package ru.altf000.multimodule.feature_recommendation_impl

import ru.altf000.multimodule.feature_recommendation_api.ReleaseManager
import ru.altf000.multimodule.feature_recommendation_impl.di.RecommendationsComponentHolder
import javax.inject.Inject

class ReleaseManagerImpl @Inject constructor() : ReleaseManager {

    override fun release() {
        RecommendationsComponentHolder.reset()
    }
}