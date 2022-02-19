package ru.altf000.multimodule.common.datasource.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

interface ReadOnlyDataSource<T> {
    val data: StateFlow<T>
}

interface UpdatableDataSource<T> {
    fun update(value: T)
}

abstract class DataSource<T>(initialValue: T) : UpdatableDataSource<T>, ReadOnlyDataSource<T> {

    override val data: MutableStateFlow<T> = MutableStateFlow(initialValue)

    override fun update(value: T) {
        data.update { value }
    }
}