package ru.altf000.multimodule.collection_list.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import kotlinx.coroutines.flow.map
import ru.altf000.multimodule.collection_list.R
import ru.altf000.multimodule.collection_list.domain.GetCollectionListUseCase
import ru.altf000.multimodule.collection_list.presentation.adapter.ContentHeaderItem
import ru.altf000.multimodule.collection_list.presentation.adapter.ContentItem
import ru.altf000.multimodule.common.viewmodel.BaseViewModel
import ru.altf000.multimodule.common_entities.domain.Content

internal class CollectionListViewModel(
    collectionListUseCase: GetCollectionListUseCase,
    collectionId: Int,
) : BaseViewModel() {

    val pager =
        collectionListUseCase(GetCollectionListUseCase.Params(collectionId))
            .map { pagingData ->
                pagingData
                    .map { content -> ContentItem(content) }
                    .insertSeparators { before, after ->
                        val beforeDate = before?.data?.year
                        val afterDate = after?.data?.year
                        if (after != null && beforeDate != afterDate) {
                            ContentHeaderItem(
                                stringsProvider.getString(
                                    R.string.content_header_item,
                                    afterDate.toString(),
                                )
                            )
                        } else {
                            null
                        }
                    }
            }
            .cachedIn(viewModelScope)

    fun onContentClicked(item: Content) = navigator.content(item)
}