package ru.altf000.multimodule.collection_list_impl.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.altf000.multimodule.collection_list_impl.di.CollectionListComponentHolder
import ru.altf000.multimodule.collection_list_impl.domain.GetCollectionListUseCase
import ru.altf000.multimodule.common.navigation.CustomRouter
import ru.altf000.multimodule.common.viewmodel.BaseViewModel
import ru.altf000.multimodule.common_entities.domain.Content
import javax.inject.Inject

internal class CollectionListViewModel @Inject constructor(
    private val getCollectionListUseCase: GetCollectionListUseCase,
    private val collectionId: Int
) : BaseViewModel() {

    private lateinit var _collectionListFlow: Flow<PagingData<Content>>
    val collectionListFlow: Flow<PagingData<Content>>
        get() = _collectionListFlow

    var router: CustomRouter? = null

    init {
        launch {
            _collectionListFlow = getCollectionListUseCase
                .execute(GetCollectionListUseCase.Params(collectionId))
                .cachedIn(viewModelScope)
        }
    }

    fun onItemClicked(item: Content) {
        router?.openMovieDetail(item)
    }

    override fun onCleared() {
        CollectionListComponentHolder.reset()
        super.onCleared()
    }
}