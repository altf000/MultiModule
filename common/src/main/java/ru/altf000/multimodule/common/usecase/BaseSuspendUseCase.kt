package ru.altf000.multimodule.common.usecase

import kotlinx.coroutines.flow.Flow

abstract class BaseSuspendUseCase<T, Params> {
    abstract suspend operator fun invoke(params: Params): Flow<T>
}