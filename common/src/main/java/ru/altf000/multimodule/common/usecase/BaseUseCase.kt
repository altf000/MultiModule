package ru.altf000.multimodule.common.usecase

import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<T, Params> {

    abstract fun execute(params: Params): Flow<T>
}