package ru.altf000.multimodule.feature_collections.presentation.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.altf000.multimodule.common_entities.domain.Block
import ru.altf000.multimodule.common_entities.mapper.toDomain
import ru.altf000.multimodule.common_entities.network.pages.BlockType
import ru.altf000.multimodule.common_network.network.adapter.result.asSuccess
import ru.altf000.multimodule.common_network.network.adapter.result.isSuccess
import ru.altf000.multimodule.common_network.network.api.ApiService

internal class CollectionsDataSource(
    private val apiService: ApiService,
) : PagingSource<Int, Block>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Block> {

        val nextPageNumber = params.key ?: 0
        val from = nextPageNumber * params.loadSize
        val to = from + params.loadSize - 1

        try {
            val apiResult = apiService.getPages(from, to)
            return if (apiResult.isSuccess()) {
                val apiItems = apiResult.asSuccess().value.result?.blocks.orEmpty()
                val mappedItems = apiItems
                    .filter { block ->
                        block.type == BlockType.COLLECTION && block.requestParams != null
                    }
                    .mapNotNull { block ->
                        val collectionResult =
                            apiService.getCollectionList(
                                block.requestParams?.id ?: -1,
                                FROM,
                                TO
                            )
                        if (collectionResult.isSuccess()) {
                            Block(
                                id = block.id,
                                title = block.title,
                                collection = collectionResult.asSuccess().value.result.map { it.toDomain() }
                            )
                        } else {
                            null
                        }
                    }
                LoadResult.Page(
                    data = mappedItems,
                    prevKey = null,
                    nextKey = if (apiItems.size == params.loadSize) {
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

    override fun getRefreshKey(state: PagingState<Int, Block>): Int = 0

    companion object {
        private const val FROM = 0
        private const val TO = 19
    }
}