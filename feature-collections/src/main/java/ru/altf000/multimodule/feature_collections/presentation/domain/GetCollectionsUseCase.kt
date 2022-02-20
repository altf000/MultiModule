package ru.altf000.multimodule.feature_collections.presentation.domain

import ru.altf000.multimodule.common.usecase.BaseSuspendUseCase
import ru.altf000.multimodule.common_entities.domain.Block
import ru.altf000.multimodule.common_network.network.adapter.RequestResult

internal class GetCollectionsUseCase(
    private val collectionsRepository: CollectionsRepository
) : BaseSuspendUseCase<RequestResult<List<Block>>, Unit>() {

    override suspend fun invoke(params: Unit) = collectionsRepository.getCollections()
}