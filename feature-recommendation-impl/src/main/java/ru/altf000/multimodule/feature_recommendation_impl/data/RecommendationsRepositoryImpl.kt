package ru.altf000.multimodule.feature_recommendation_impl.data

import kotlinx.coroutines.flow.flow
import ru.altf000.multimodule.common_db.db.AppDatabase
import ru.altf000.multimodule.common_entities.mapper.toDomain
import ru.altf000.multimodule.common_entities.mapper.toEntity
import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import ru.altf000.multimodule.common_network.network.adapter.result.asSuccess
import ru.altf000.multimodule.common_network.network.adapter.result.isSuccess
import ru.altf000.multimodule.common_network.network.adapter.result.map
import ru.altf000.multimodule.common_network.network.api.ApiService
import ru.altf000.multimodule.feature_recommendation_impl.domain.RecommendationsRepository

internal class RecommendationsRepositoryImpl(
    private val apiService: ApiService,
    private val database: AppDatabase,
) : RecommendationsRepository {

    override fun getContentRecommendations(contentId: Int) = flow {

        val dao = database.recommendationsDao()
        val cachedItems = dao.getRecommendations(contentId)

        if (cachedItems.isNotEmpty()) {
            emit(RequestResult.Success.Value(cachedItems.map { it.toDomain() }))
            return@flow
        }

        val apiResult = apiService
            .getRecommendations(contentId, ITEM_PAGE)
            .map { response -> response.result.map { it.toDomain() } }

        emit(apiResult)

        if (apiResult.isSuccess()) {
            val recommendationsEntities = apiResult.asSuccess().value.map { it.toEntity(contentId) }
            dao.deleteAll(contentId)
            dao.insertAll(recommendationsEntities)
        }
    }

    companion object {
        const val ITEM_PAGE = "ITEM_PAGE"
    }
}