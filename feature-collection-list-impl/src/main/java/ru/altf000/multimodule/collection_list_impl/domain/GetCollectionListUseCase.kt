package ru.altf000.multimodule.collection_list_impl.domain

import androidx.paging.PagingData
import ru.altf000.multimodule.common.di.ScopeScreen
import ru.altf000.multimodule.common.usecase.BaseUseCase
import ru.altf000.multimodule.common_entities.domain.Content
import javax.inject.Inject

@ScopeScreen
internal class GetCollectionListUseCase @Inject constructor(
    private val collectionListRepository: CollectionListRepository
) : BaseUseCase<PagingData<Content>, GetCollectionListUseCase.Params>() {

    override fun execute(params: Params) =
        collectionListRepository.getCollectionList(params.collectionId)

    class Params(val collectionId: Int)
}