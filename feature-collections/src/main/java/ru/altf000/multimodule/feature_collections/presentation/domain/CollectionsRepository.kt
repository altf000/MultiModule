package ru.altf000.multimodule.feature_collections.presentation.domain

import kotlinx.coroutines.flow.Flow
import ru.altf000.multimodule.common_entities.domain.Block
import ru.altf000.multimodule.common_network.network.adapter.RequestResult

internal interface CollectionsRepository {
    fun getCollections(): Flow<RequestResult<List<Block>>>
}