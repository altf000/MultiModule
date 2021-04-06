package ru.altf000.multimodule.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.altf000.multimodule.common_db.db.AppDatabase
import ru.altf000.multimodule.common_db.di.DatabaseComponent
import ru.altf000.multimodule.common_network.di.NetworkComponent
import ru.altf000.multimodule.common_network.network.api.ApiService
import ru.altf000.multimodule.data.MainRepositoryImpl
import ru.altf000.multimodule.domain.MainRepository
import javax.inject.Singleton

@Module
internal class RepositoryModule {

    @Singleton
    @Provides
    fun apiService(): ApiService {
        return NetworkComponent.get().apiService()
    }

    @Singleton
    @Provides
    fun database(context: Context): AppDatabase {
        return DatabaseComponent.get(context).getDatabase()
    }

    @Singleton
    @Provides
    fun provideMainRepository(mainRepository: MainRepositoryImpl): MainRepository {
        return mainRepository
    }
}