package ru.altf000.multimodule.feature_collection_list.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import ru.altf000.multimodule.feature_collection_list.data.paging.CollectionListDataSource
import ru.altf000.multimodule.feature_collection_list.domain.CollectionListRepository
import ru.altf000.multimodule.common_network.network.api.ApiService

private const val PAGE_SIZE = 20

internal class CollectionListRepositoryImpl(
    private val apiService: ApiService,
) : CollectionListRepository {

    override fun getCollectionList(collectionId: Int) = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { CollectionListDataSource(apiService, collectionId) }
    ).flow
}