package ru.altf000.multimodule.common.usecase

import kotlinx.coroutines.flow.Flow

abstract class BaseSuspendUseCase<T, Params> {

    abstract suspend fun execute(params: Params): Flow<T>
}