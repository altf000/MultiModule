package ru.altf000.multimodule.feature_recommendation_impl.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.altf000.multimodule.common.di.ScopeFeature
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_entities.mapper.toLocal
import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import ru.altf000.multimodule.common_network.network.adapter.result.map
import ru.altf000.multimodule.common_network.network.api.ApiService
import ru.altf000.multimodule.feature_recommendation_impl.domain.RecommendationsRepository
import javax.inject.Inject

@ScopeFeature
internal class RecommendationsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : RecommendationsRepository {

    override fun getContentRecommendations(contentId: Int): Flow<RequestResult<List<Content>>> = flow {
        emit(
            apiService.getRecommendations(contentId, ITEM_PAGE).map { response ->
                response.result.map { it.toLocal() }
            }
        )
    }

    companion object {
        const val ITEM_PAGE = "ITEM_PAGE"
    }
}