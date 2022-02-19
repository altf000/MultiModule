package ru.altf000.multimodule.common_db.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.altf000.multimodule.common_db.db.AppDatabase

val dbModule = module {
    single { AppDatabase.getInstance(androidContext()) }
    single { get<AppDatabase>().countriesDao() }
    single { get<AppDatabase>().fullContentDao() }
    single { get<AppDatabase>().genresDao() }
    single { get<AppDatabase>().recommendationsDao() }
}