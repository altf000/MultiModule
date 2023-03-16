package ru.altf000.multimodule.common.di

import org.koin.dsl.binds
import org.koin.dsl.module
import ru.altf000.multimodule.common.datasource.CountriesDataSource
import ru.altf000.multimodule.common.datasource.CountriesReadOnlyDataSource
import ru.altf000.multimodule.common.datasource.CountriesUpdatableDataSource
import ru.altf000.multimodule.common.datasource.GenresDataSource
import ru.altf000.multimodule.common.datasource.GenresReadOnlyDataSource
import ru.altf000.multimodule.common.datasource.GenresUpdatableDataSource

val commonModule = module {
    single { CountriesDataSource() }.binds(
        arrayOf(
            CountriesReadOnlyDataSource::class,
            CountriesUpdatableDataSource::class
        )
    )
    single { GenresDataSource() }.binds(
        arrayOf(
            GenresReadOnlyDataSource::class,
            GenresUpdatableDataSource::class
        )
    )
}