package ru.altf000.multimodule.common_db.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.altf000.multimodule.common_db.db.AppDatabase
import javax.inject.Singleton

@Module
internal class DatabaseModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideDatabase(): AppDatabase {
        return AppDatabase.getInstance(context)
    }
}