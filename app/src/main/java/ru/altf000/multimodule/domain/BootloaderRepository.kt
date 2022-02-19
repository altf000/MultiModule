package ru.altf000.multimodule.domain

import kotlinx.coroutines.flow.Flow

interface BootloaderRepository {
    fun load(): Flow<Boolean>
}