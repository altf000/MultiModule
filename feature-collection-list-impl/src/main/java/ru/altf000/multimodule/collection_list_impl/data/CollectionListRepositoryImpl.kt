package ru.altf000.multimodule.collection_list_impl.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.altf000.multimodule.collection_list_impl.domain.CollectionListRepository
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_entities.mapper.toLocal
import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import ru.altf000.multimodule.common_network.network.adapter.result.map
import ru.altf000.multimodule.common_network.network.api.ApiService
import ru.altf000.multimodule.common.di.ScopeFeature
import ru.altf000.multimodule.common.di.ScopeScreen
import javax.inject.Inject

@ScopeScreen
internal class CollectionListRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CollectionListRepository {

    override fun getCollectionList(id: Int, page: Int, pageSize: Int)
        : Flow<RequestResult<List<Content>>> = flow {

        val from: Int = page * pageSize
        val to = from + pageSize - 1
        emit(
            apiService.getCollectionList(id, from, to).map { response ->
                response.result.map { it.toLocal() }
            }
        )
    }
}