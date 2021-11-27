package ru.altf000.multimodule.collection_list_impl.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.altf000.multimodule.common_entities.domain.Content

internal interface CollectionListRepository {
    fun getCollectionList(collectionId: Int): Flow<PagingData<Content>>
}