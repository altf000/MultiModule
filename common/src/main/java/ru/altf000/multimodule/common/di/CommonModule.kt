package ru.altf000.multimodule.common.di

import org.koin.dsl.bind
import org.koin.dsl.module
import ru.altf000.multimodule.common.datasource.*

val commonModule = module {
    single { CountriesDataSource() }.bind<CountriesReadOnlyDataSource>().bind<CountriesUpdatableDataSource>()
    single { GenresDataSource() }.bind<GenresReadOnlyDataSource>().bind<GenresUpdatableDataSource>()
}