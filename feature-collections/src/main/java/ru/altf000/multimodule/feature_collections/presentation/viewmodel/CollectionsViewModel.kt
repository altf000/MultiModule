package ru.altf000.multimodule.feature_collections.presentation.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.altf000.multimodule.common.viewmodel.BaseViewModel
import ru.altf000.multimodule.common_entities.domain.Block
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_network.network.adapter.ErrorResult
import ru.altf000.multimodule.common_network.network.adapter.SuccessResult
import ru.altf000.multimodule.feature_collections.presentation.domain.GetCollectionsUseCase
import ru.altf000.multimodule.feature_collections.presentation.view.adapter.HorizontalItem
import ru.altf000.multimodule.feature_collections.presentation.view.adapter.HorizontalItemData
import ru.altf000.multimodule.feature_collections.presentation.view.adapter.MovieItem
import timber.log.Timber

internal class CollectionsViewModel(
    private val getCollectionsUseCase: GetCollectionsUseCase
) : BaseViewModel() {

    private val collectionsList = MutableStateFlow(emptyList<Block>())
    val collectionItems = collectionsList.map { list ->
        list.map { block ->
            HorizontalItem(
                itemData = HorizontalItemData(
                    title = block.title,
                    items = block.collection.map { content ->
                        MovieItem(content)
                    }
                )
            )
        }
    }

    private val _isRefreshing = MutableStateFlow(true)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        load()
    }

    fun onItemClicked(item: Content) {
        navigator.movieDetail(item)
    }

    fun refresh() {
        _isRefreshing.value = true
        load()
    }

    private fun load() {
        launch {
            getCollectionsUseCase(Unit).collect { result ->
                when (result) {
                    is SuccessResult -> {
                        _isRefreshing.value = false
                        collectionsList.value = result.value
                    }
                    is ErrorResult -> {
                        _isRefreshing.value = false
                        Timber.e("Error to get collections: ${result.error}")
                    }
                }
            }
        }
    }
}