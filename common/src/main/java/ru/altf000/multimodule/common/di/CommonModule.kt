package ru.altf000.multimodule.common.di

import org.koin.dsl.binds
import org.koin.dsl.module
import ru.altf000.multimodule.common.datasource.*

val commonModule = module {
    single { CountriesDataSource() }.binds(arrayOf(CountriesReadOnlyDataSource::class, CountriesUpdatableDataSource::class))
    single { GenresDataSource() }.binds(arrayOf(GenresReadOnlyDataSource::class, GenresUpdatableDataSource::class))
}