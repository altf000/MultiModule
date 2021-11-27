package ru.altf000.multimodule.collection_list_impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.altf000.multimodule.collection_list_impl.domain.GetCollectionListUseCase

internal class CollectionListViewModelFactory @AssistedInject constructor(
    @Assisted private val collectionId: Int,
    private val collectionListUseCase: GetCollectionListUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CollectionListViewModel(collectionListUseCase, collectionId) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted collectionId: Int): CollectionListViewModelFactory
    }
}