package ru.altf000.multimodule.feature_recommendation_impl

import ru.altf000.multimodule.common.di.ScopeFeature
import ru.altf000.multimodule.feature_recommendation_api.Utils
import ru.altf000.multimodule.feature_recommendation_impl.di.RecommendationsComponentHolder
import javax.inject.Inject

@ScopeFeature
class UtilsImpl @Inject constructor() : Utils {

    override fun release() {
        RecommendationsComponentHolder.reset()
    }
}