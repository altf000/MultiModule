package ru.altf000.multimodule.movie_detail.data

import kotlinx.coroutines.flow.flow
import ru.altf000.multimodule.common_db.db.AppDatabase
import ru.altf000.multimodule.common_entities.mapper.toDomain
import ru.altf000.multimodule.common_entities.mapper.toEntity
import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import ru.altf000.multimodule.common_network.network.adapter.result.asSuccess
import ru.altf000.multimodule.common_network.network.adapter.result.isSuccess
import ru.altf000.multimodule.common_network.network.adapter.result.map
import ru.altf000.multimodule.common_network.network.api.ApiService
import ru.altf000.multimodule.movie_detail.domain.MovieDetailRepository

internal class MovieDetailRepositoryImpl(
    private val apiService: ApiService,
    private val database: AppDatabase,
) : MovieDetailRepository {

    override fun getMovieInfo(contentId: Int) = getContent(contentId, false)

    override fun getSerialInfo(contentId: Int) = getContent(contentId, true)

    private fun getContent(contentId: Int, isSerial: Boolean) = flow {

        val dao = database.fullContentDao()
        dao.findById(contentId)?.let { entity ->
            emit(RequestResult.Success.Value(entity.toDomain()))
        } ?: run {

            val apiResult = if (isSerial) {
                apiService.getSerial(contentId).map { it.result.toDomain() }
            } else {
                apiService.getMovie(contentId).map { it.result.toDomain() }
            }
            emit(apiResult)

            if (apiResult.isSuccess()) {
                dao.insert(apiResult.asSuccess().value.toEntity())
            }
        }
    }
}