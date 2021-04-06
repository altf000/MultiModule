package ru.altf000.multimodule.collection_list_impl.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import ru.altf000.multimodule.common.usecase.PagingUseCase
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import ru.altf000.multimodule.common_network.network.adapter.result.asFailure
import ru.altf000.multimodule.common_network.network.adapter.result.asSuccess
import ru.altf000.multimodule.common_network.network.adapter.result.isSuccess
import ru.altf000.multimodule.common.di.ScopeFeature
import ru.altf000.multimodule.common.di.ScopeScreen
import javax.inject.Inject

@ScopeScreen
internal class GetCollectionListUseCase @Inject constructor(
    private val collectionListRepository: CollectionListRepository
) : PagingUseCase<RequestResult<List<Content>>, GetCollectionListUseCase.Params, Content>() {

    override suspend fun execute(params: Params): Flow<RequestResult<List<Content>>> {

        if (!canLoad(params)) {
            return emptyFlow()
        }

        return flow {

            startLoading()

            val currentPage = if (params.isLoadMore)
                lastLoadedPage + 1
            else
                lastLoadedPage

            try {
                val result = collectionListRepository
                    .getCollectionList(params.collectionId, currentPage, DEFAULT_PAGE_SIZE)
                    .first()
                if (result.isSuccess()) {
                    saveList(result.asSuccess().value, currentPage)
                    emit(RequestResult.Success.Value(currentList))
                } else {
                    emit(result.asFailure())
                }
            } catch (exc: Exception) {
                emit(RequestResult.Failure.Error(exc))
            }

            stopLoading()
        }
    }

    class Params(val collectionId: Int, isLoadMore: Boolean) : PagingUseCase.Params(isLoadMore)
}