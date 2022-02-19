package ru.altf000.multimodule.collection_list.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import ru.altf000.multimodule.collection_list.domain.GetCollectionListUseCase
import ru.altf000.multimodule.common.viewmodel.BaseViewModel
import ru.altf000.multimodule.common_entities.domain.Content

internal class CollectionListViewModel(
    collectionListUseCase: GetCollectionListUseCase,
    collectionId: Int
) : BaseViewModel() {

    val collectionListFlow: Flow<PagingData<Content>> =
        collectionListUseCase(GetCollectionListUseCase.Params(collectionId)).cachedIn(viewModelScope)

    fun onItemClicked(item: Content) {
        navigator.movieDetail(item)
    }
}