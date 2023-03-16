package ru.altf000.multimodule.feature_collections.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.map
import ru.altf000.multimodule.common.viewmodel.BaseViewModel
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.feature_collections.presentation.domain.GetCollectionsUseCase
import ru.altf000.multimodule.feature_collections.presentation.view.adapter.ContentItem
import ru.altf000.multimodule.feature_collections.presentation.view.adapter.HorizontalItem
import ru.altf000.multimodule.feature_collections.presentation.view.adapter.HorizontalItemData

internal class CollectionsViewModel(
    getCollectionsUseCase: GetCollectionsUseCase,
) : BaseViewModel() {

    val collectionItems = getCollectionsUseCase(Unit)
        .map { pagingData ->
            pagingData.map { block ->
                HorizontalItem(
                    itemData = HorizontalItemData(
                        title = block.title,
                        collectionId = block.id,
                        items = block.collection.map { content ->
                            ContentItem(content)
                        }
                    )
                )
            }
        }
        .cachedIn(viewModelScope)

    fun onHeaderClicked(collectionId: Int) = navigator.collection(collectionId)

    fun onContentClicked(item: Content) = navigator.content(item)
}