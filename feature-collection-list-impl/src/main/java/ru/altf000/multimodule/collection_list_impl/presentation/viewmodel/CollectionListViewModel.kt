package ru.altf000.multimodule.collection_list_impl.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import ru.altf000.multimodule.collection_list_impl.di.CollectionListComponentHolder
import ru.altf000.multimodule.collection_list_impl.domain.GetCollectionListUseCase
import ru.altf000.multimodule.common.navigation.GlobalRouter
import ru.altf000.multimodule.common.viewmodel.BaseViewModel
import ru.altf000.multimodule.common_entities.domain.Content

internal class CollectionListViewModel(
    collectionListUseCase: GetCollectionListUseCase,
    collectionId: Int
) : BaseViewModel() {

    val collectionListFlow: Flow<PagingData<Content>> = collectionListUseCase
        .execute(GetCollectionListUseCase.Params(collectionId))
        .cachedIn(viewModelScope)

    var router: GlobalRouter? = null

    fun onItemClicked(item: Content) {
        router?.openMovieDetail(item)
    }

    override fun onCleared() {
        CollectionListComponentHolder.reset()
        super.onCleared()
    }
}