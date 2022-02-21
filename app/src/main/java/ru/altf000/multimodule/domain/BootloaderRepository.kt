package ru.altf000.multimodule.domain

import kotlinx.coroutines.flow.Flow

internal interface BootloaderRepository {
    fun load(): Flow<Boolean>
}