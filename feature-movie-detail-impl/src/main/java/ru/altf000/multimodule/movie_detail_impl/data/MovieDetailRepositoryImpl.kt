package ru.altf000.multimodule.movie_detail_impl.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.altf000.multimodule.common.di.ScopeScreen
import ru.altf000.multimodule.common_db.db.AppDatabase
import ru.altf000.multimodule.common_entities.domain.FullContent
import ru.altf000.multimodule.common_entities.mapper.toEntity
import ru.altf000.multimodule.common_entities.mapper.toLocal
import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import ru.altf000.multimodule.common_network.network.adapter.result.asSuccess
import ru.altf000.multimodule.common_network.network.adapter.result.isSuccess
import ru.altf000.multimodule.common_network.network.adapter.result.map
import ru.altf000.multimodule.common_network.network.api.ApiService
import ru.altf000.multimodule.movie_detail_impl.domain.MovieDetailRepository
import javax.inject.Inject

@ScopeScreen
internal class MovieDetailRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val database: AppDatabase,
) : MovieDetailRepository {

    override fun getMovieInfo(contentId: Int):
        Flow<RequestResult<FullContent>> = getContent(contentId, false)

    override fun getSerialInfo(contentId: Int):
        Flow<RequestResult<FullContent>> = getContent(contentId, true)

    private fun getContent(contentId: Int, isSerial: Boolean):
        Flow<RequestResult<FullContent>> = flow {

        val dao = database.fullContentDao()
        dao.findById(contentId)?.let {
            emit(RequestResult.Success.Value(it.toLocal()))
        } ?: run {

            val apiResult = if (isSerial) {
                apiService.getSerial(contentId).map { it.result.toLocal() }
            } else {
                apiService.getMovie(contentId).map { it.result.toLocal() }
            }
            emit(apiResult)

            if (apiResult.isSuccess()) {
                dao.insert(apiResult.asSuccess().value.toEntity())
            }
        }
    }
}