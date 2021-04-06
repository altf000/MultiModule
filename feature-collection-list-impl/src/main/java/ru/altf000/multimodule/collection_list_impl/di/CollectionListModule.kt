package ru.altf000.multimodule.collection_list_impl.di

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ru.altf000.multimodule.collection_list_api.CollectionScreenCreator
import ru.altf000.multimodule.collection_list_impl.data.CollectionListRepositoryImpl
import ru.altf000.multimodule.collection_list_impl.domain.CollectionListRepository
import ru.altf000.multimodule.collection_list_impl.presentation.viewmodel.CollectionListViewModelFactory
import ru.altf000.multimodule.collection_list_impl.start.CollectionScreenCreatorImpl
import ru.altf000.multimodule.common.di.ScopeScreen

@Module
internal class CollectionListModule {

    @ScopeScreen
    @Provides
    fun provideRepository(collectionRepository: CollectionListRepositoryImpl): CollectionListRepository {
        return collectionRepository
    }

    @ScopeScreen
    @Provides
    fun provideScreenCreator(screenCreator: CollectionScreenCreatorImpl): CollectionScreenCreator {
        return screenCreator
    }

    @ScopeScreen
    @Provides
    fun provideViewModelFactory(factory: CollectionListViewModelFactory): ViewModelProvider.Factory {
        return factory
    }
}