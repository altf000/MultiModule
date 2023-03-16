package ru.altf000.multimodule.feature_collections.presentation.domain

import androidx.paging.PagingData
import ru.altf000.multimodule.common.usecase.BaseUseCase
import ru.altf000.multimodule.common_entities.domain.Block

internal class GetCollectionsUseCase(
    private val collectionsRepository: CollectionsRepository,
) : BaseUseCase<PagingData<Block>, Unit>() {

    override fun invoke(params: Unit) = collectionsRepository.getCollections()
}