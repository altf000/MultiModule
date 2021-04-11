package ru.altf000.multimodule.collection_list_impl.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.altf000.multimodule.collection_list_impl.di.CollectionListComponentHolder
import ru.altf000.multimodule.collection_list_impl.domain.GetCollectionListUseCase
import ru.altf000.multimodule.common.navigation.CustomRouter
import ru.altf000.multimodule.common.viewmodel.BaseViewModel
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import javax.inject.Inject

internal class CollectionListViewModel @Inject constructor(
    private val getCollectionListUseCase: GetCollectionListUseCase
) : BaseViewModel() {

    private val _collectionList = MutableLiveData<List<Content>>().apply {
        value = listOf(Content(), Content(), Content())
    }
    val collectionList: LiveData<List<Content>> get() = _collectionList

    private val _collectionListError = MutableLiveData<Boolean>()
    val collectionListError: LiveData<Boolean> get() = _collectionListError

    var router: CustomRouter? = null
    var collectionId = -1

    @Volatile
    private var isFirstPageLoaded = false

    fun loadFirstPage() {
        if (!isFirstPageLoaded) {
            launch {
                getCollectionListUseCase
                    .execute(GetCollectionListUseCase.Params(collectionId, false))
                    .collect {
                        isFirstPageLoaded = true
                        onResult(it)
                    }
            }
        }
    }

    fun loadMore() {
        launch {
            getCollectionListUseCase
                .execute(GetCollectionListUseCase.Params(collectionId, true))
                .collect { onResult(it) }
        }
    }

    fun refresh() {
        cancelChildrenJobs()
        isFirstPageLoaded = false
        getCollectionListUseCase.reset()
        loadFirstPage()
    }

    private suspend fun onResult(result: RequestResult<List<Content>>) {
        return withContext(Dispatchers.Main) {
            when (result) {
                is RequestResult.Success -> {
                    _collectionList.value = result.value
                    _collectionListError.value = false
                }
                is RequestResult.Failure<*> -> {
                    _collectionList.value = emptyList()
                    _collectionListError.value = true
                }
            }
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