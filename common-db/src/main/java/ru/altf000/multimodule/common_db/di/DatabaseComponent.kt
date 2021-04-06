package ru.altf000.multimodule.common_db.di

import android.content.Context
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DatabaseModule::class])
@Singleton
abstract class DatabaseComponent : DatabaseApi {

    companion object {

        @Volatile
        private var databaseComponent: DatabaseComponent? = null

        fun get(context: Context): DatabaseComponent {
            if (databaseComponent == null) {
                synchronized(DatabaseComponent::class.java) {
                    if (databaseComponent == null) {
                        databaseComponent = DaggerDatabaseComponent
                            .builder()
                            .databaseModule(DatabaseModule(context))
                            .build()
                    }
                }
            }
            return databaseComponent!!
        }
    }
}