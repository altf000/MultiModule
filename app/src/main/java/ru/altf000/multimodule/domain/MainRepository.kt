package ru.altf000.multimodule.domain

import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun loadRequireData(): Flow<Boolean>
}