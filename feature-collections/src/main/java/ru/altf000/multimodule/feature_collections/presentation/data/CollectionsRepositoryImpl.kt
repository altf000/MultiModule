package ru.altf000.multimodule.feature_collections.presentation.data

import kotlinx.coroutines.flow.flow
import ru.altf000.multimodule.common_entities.domain.Block
import ru.altf000.multimodule.common_entities.mapper.toDomain
import ru.altf000.multimodule.common_entities.network.pages.BlockType
import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import ru.altf000.multimodule.common_network.network.adapter.result.asFailure
import ru.altf000.multimodule.common_network.network.adapter.result.asSuccess
import ru.altf000.multimodule.common_network.network.adapter.result.isSuccess
import ru.altf000.multimodule.common_network.network.api.ApiService
import ru.altf000.multimodule.feature_collections.presentation.domain.CollectionsRepository

internal class CollectionsRepositoryImpl(
    private val apiService: ApiService
) : CollectionsRepository {

    override fun getCollections() = flow {
        val apiResult = apiService.getPages()
        if (apiResult.isSuccess()) {
            val blocksListNetwork = apiResult.asSuccess().value.result?.blocks.orEmpty()
            val blocksList = blocksListNetwork
                .filter { block ->
                    block.type == BlockType.COLLECTION && block.requestParams != null
                }
                .mapNotNull { block ->
                    val collectionResult =
                        apiService.getCollectionList(block.requestParams!!.id, FROM, TO)
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
            emit(RequestResult.Success.Value(blocksList))
        } else {
            emit(apiResult.asFailure())
        }
    }

    companion object {
        private const val FROM = 0
        private const val TO = 19
    }
}