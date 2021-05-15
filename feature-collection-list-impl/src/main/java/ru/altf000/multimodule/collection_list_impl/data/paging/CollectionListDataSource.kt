package ru.altf000.multimodule.collection_list_impl.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_entities.mapper.toFullContent
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
        val from = nextPageNumber * params.loadSize
        val to = from + params.loadSize - 1

        Timber.tag("CollectionPaging")
            .d("nextPageNumber = $nextPageNumber, loadSize = ${params.loadSize}")

        try {

            val apiResult = apiService
                .getCollectionList(collectionId, from, to)
                .map { response ->
                    response.result.map { it.toFullContent() }
                }

            return if (apiResult.isSuccess()) {
                val items = apiResult.asSuccess().value
                LoadResult.Page(
                    data = items,
                    prevKey = null,
                    nextKey = if (items.size == params.loadSize) {
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

    override fun getRefreshKey(state: PagingState<Int, Content>): Int = 0
}