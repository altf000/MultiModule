package ru.altf000.multimodule.movie_detail_impl.domain

import kotlinx.coroutines.flow.Flow
import ru.altf000.multimodule.common.di.ScopeScreen
import ru.altf000.multimodule.common.usecase.BaseSuspendUseCase
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_entities.domain.FullContent
import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import javax.inject.Inject

@ScopeScreen
class GetContentInfoUseCase @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository
) : BaseSuspendUseCase<RequestResult<FullContent>, GetContentInfoUseCase.Params>() {

    override suspend fun execute(params: Params): Flow<RequestResult<FullContent>> {
        return if (params.content.isSerial) {
            movieDetailRepository.getSerialInfo(params.content.id)
        } else {
            movieDetailRepository.getMovieInfo(params.content.id)
        }
    }

    data class Params(val content: Content)
}