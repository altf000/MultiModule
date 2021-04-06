package ru.altf000.multimodule.common_db.di

import ru.altf000.multimodule.common_db.db.AppDatabase

interface DatabaseApi {

    fun getDatabase(): AppDatabase
}