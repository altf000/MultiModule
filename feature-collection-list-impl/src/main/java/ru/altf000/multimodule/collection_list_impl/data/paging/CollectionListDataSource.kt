package ru.altf000.multimodule.collection_list_impl.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_entities.mapper.toContent
import ru.altf000.multimodule.common_network.network.adapter.result.asSuccess
import ru.altf000.multimodule.common_network.network.adapter.result.isSuccess
import ru.altf000.multimodule.common_network.network.adapter.result.map
import ru.altf000.multimodule.common_network.network.api.ApiService
import timber.log.Timber

class CollectionListDataSource(
    private val apiService: ApiService,
    private val collectionId: Int
) : PagingSource<Int, Content>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Content> {

        val nextPageNumber = params.key ?: 0
        val from = nextPageNumber * PAGE_SIZE
        val to = from + PAGE_SIZE - 1

        Timber.tag("CollectionPaging").d("nextPageNumber = $nextPageNumber")

        try {

            val result = apiService
                .getCollectionList(collectionId, from, to)
                .map { response ->
                    response.result.map { it.toContent() }
                }

            return if (result.isSuccess()) {
                val items = result.asSuccess().value
                LoadResult.Page(
                    data = items,
                    prevKey = null,
                    nextKey = if (items.size == PAGE_SIZE) {
                        nextPageNumber + 1
                    } else {
                        null
                    }
                )
            } else {
                LoadResult.Error(Exception())
            }

        } catch (exc: Exception) {
            return LoadResult.Error(exc)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Content>): Int? = 0
}

const val PAGE_SIZE = 20