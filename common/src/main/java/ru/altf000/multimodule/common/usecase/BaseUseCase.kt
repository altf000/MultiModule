package ru.altf000.multimodule.common.usecase

import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<T, Params> {
    abstract operator fun invoke(params: Params): Flow<T>
}