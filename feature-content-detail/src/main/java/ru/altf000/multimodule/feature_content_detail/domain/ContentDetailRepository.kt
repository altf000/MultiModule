package ru.altf000.multimodule.feature_content_detail.domain

import kotlinx.coroutines.flow.Flow
import ru.altf000.multimodule.common_entities.domain.FullContent
import ru.altf000.multimodule.common_network.network.adapter.RequestResult

internal interface ContentDetailRepository {
    fun getMovieInfo(contentId: Int): Flow<RequestResult<FullContent>>
    fun getSerialInfo(contentId: Int): Flow<RequestResult<FullContent>>
}