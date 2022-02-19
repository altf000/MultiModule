package ru.altf000.multimodule.movie_detail.domain

import ru.altf000.multimodule.common.usecase.BaseSuspendUseCase
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common_entities.domain.FullContent
import ru.altf000.multimodule.common_network.network.adapter.RequestResult

internal class GetContentInfoUseCase(
    private val movieDetailRepository: MovieDetailRepository
) : BaseSuspendUseCase<RequestResult<FullContent>, GetContentInfoUseCase.Params>() {

    override suspend fun invoke(params: Params) = if (params.content.isSerial) {
        movieDetailRepository.getSerialInfo(params.content.id)
    } else {
        movieDetailRepository.getMovieInfo(params.content.id)
    }

    data class Params(val content: Content)
}