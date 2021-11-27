package ru.altf000.multimodule.collection_list_impl.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import ru.altf000.multimodule.collection_list_impl.data.paging.CollectionListDataSource
import ru.altf000.multimodule.collection_list_impl.domain.CollectionListRepository
import ru.altf000.multimodule.common_network.network.api.ApiService
import javax.inject.Inject

const val PAGE_SIZE = 20

internal class CollectionListRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CollectionListRepository {

    override fun getCollectionList(collectionId: Int) = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE),
        pagingSourceFactory = { CollectionListDataSource(apiService, collectionId) }
    ).flow
}