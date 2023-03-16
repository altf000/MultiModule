package ru.altf000.multimodule.collection_list.domain

import androidx.paging.PagingData
import ru.altf000.multimodule.common.usecase.BaseUseCase
import ru.altf000.multimodule.common_entities.domain.Content

internal class GetCollectionListUseCase(
    private val collectionListRepository: CollectionListRepository,
) : BaseUseCase<PagingData<Content>, GetCollectionListUseCase.Params>() {

    override fun invoke(params: Params) =
        collectionListRepository.getCollectionList(params.collectionId)

    internal data class Params(val collectionId: Int)
}