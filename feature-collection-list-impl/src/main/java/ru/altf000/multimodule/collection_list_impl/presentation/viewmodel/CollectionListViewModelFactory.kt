package ru.altf000.multimodule.collection_list_impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.altf000.multimodule.collection_list_impl.domain.GetCollectionListUseCase
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
internal class CollectionListViewModelFactory @Inject constructor(
    private val getCollectionListUseCase: GetCollectionListUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CollectionListViewModel(getCollectionListUseCase) as T
    }
}