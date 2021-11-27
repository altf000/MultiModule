package ru.altf000.multimodule.movie_detail_impl.domain

import kotlinx.coroutines.flow.Flow
import ru.altf000.multimodule.common_entities.domain.FullContent
import ru.altf000.multimodule.common_network.network.adapter.RequestResult

interface MovieDetailRepository {
    fun getMovieInfo(contentId: Int): Flow<RequestResult<FullContent>>
    fun getSerialInfo(contentId: Int): Flow<RequestResult<FullContent>>
}