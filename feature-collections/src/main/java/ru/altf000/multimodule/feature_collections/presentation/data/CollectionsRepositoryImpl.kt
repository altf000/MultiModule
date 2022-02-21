package ru.altf000.multimodule.feature_collections.presentation.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import ru.altf000.multimodule.common_network.network.api.ApiService
import ru.altf000.multimodule.feature_collections.presentation.data.paging.CollectionsDataSource
import ru.altf000.multimodule.feature_collections.presentation.domain.CollectionsRepository

private const val PAGE_SIZE = 10

internal class CollectionsRepositoryImpl(
    private val apiService: ApiService
) : CollectionsRepository {

    override fun getCollections() = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { CollectionsDataSource(apiService) }
    ).flow
}