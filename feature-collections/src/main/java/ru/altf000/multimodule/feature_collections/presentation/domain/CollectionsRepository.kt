package ru.altf000.multimodule.feature_collections.presentation.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.altf000.multimodule.common_entities.domain.Block

internal interface CollectionsRepository {
    fun getCollections(): Flow<PagingData<Block>>
}