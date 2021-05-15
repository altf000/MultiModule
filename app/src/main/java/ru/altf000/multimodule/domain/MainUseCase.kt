package ru.altf000.multimodule.domain

import kotlinx.coroutines.flow.Flow
import ru.altf000.multimodule.common.usecase.BaseSuspendUseCase
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val mainRepository: MainRepository
) : BaseSuspendUseCase<Boolean, Unit>() {

    override suspend fun execute(params: Unit): Flow<Boolean> {
        return mainRepository.loadRequireData()
    }
}