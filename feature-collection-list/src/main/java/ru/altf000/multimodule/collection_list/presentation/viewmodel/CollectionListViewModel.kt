package ru.altf000.multimodule.collection_list.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.map
import ru.altf000.multimodule.collection_list.domain.GetCollectionListUseCase
import ru.altf000.multimodule.collection_list.presentation.adapter.ContentItem
import ru.altf000.multimodule.common.viewmodel.BaseViewModel
import ru.altf000.multimodule.common_entities.domain.Content

internal class CollectionListViewModel(
    collectionListUseCase: GetCollectionListUseCase,
    collectionId: Int
) : BaseViewModel() {

    val pager =
        collectionListUseCase(GetCollectionListUseCase.Params(collectionId))
            .map { pagingData ->
                pagingData.map { content ->
                    ContentItem(content)
                }
            }
            .cachedIn(viewModelScope)

    fun onItemClicked(item: Content) {
        navigator.movieDetail(item)
    }
}