package ru.altf000.multimodule.collection_list_impl.domain

import kotlinx.coroutines.flow.Flow
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_network.network.adapter.RequestResult

internal interface CollectionListRepository {

    fun getCollectionList(id: Int, page: Int, pageSize: Int): Flow<RequestResult<List<Content>>>
}