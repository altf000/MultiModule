package ru.altf000.multimodule.feature_content_detail.data

import kotlinx.coroutines.flow.flow
import ru.altf000.multimodule.common_db.db.AppDatabase
import ru.altf000.multimodule.common_entities.mapper.toDomain
import ru.altf000.multimodule.common_entities.mapper.toEntity
import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import ru.altf000.multimodule.common_network.network.adapter.result.asSuccess
import ru.altf000.multimodule.common_network.network.adapter.result.isSuccess
import ru.altf000.multimodule.common_network.network.adapter.result.mapIfSuccess
import ru.altf000.multimodule.common_network.network.api.ApiService
import ru.altf000.multimodule.feature_content_detail.domain.ContentDetailRepository

internal class ContentDetailRepositoryImpl(
    private val apiService: ApiService,
    private val database: AppDatabase,
) : ContentDetailRepository {

    override fun getMovieInfo(contentId: Int) = getContent(contentId, false)

    override fun getSerialInfo(contentId: Int) = getContent(contentId, true)

    private fun getContent(contentId: Int, isSerial: Boolean) = flow {

        val dao = database.fullContentDao()
        val cachedContent = dao.findById(contentId)

        if (cachedContent != null) {
            emit(RequestResult.Success.Value(cachedContent.toDomain()))
            return@flow
        }

        val apiResult = if (isSerial) {
            apiService
                .getSerial(contentId)
                .mapIfSuccess { it.result.toDomain() }
        } else {
            apiService
                .getMovie(contentId)
                .mapIfSuccess { it.result.toDomain() }
        }

        emit(apiResult)

        if (apiResult.isSuccess()) {
            dao.insert(apiResult.asSuccess().value.toEntity())
        }
    }
}