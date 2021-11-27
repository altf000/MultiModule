package ru.altf000.multimodule.collection_list_impl.di

import dagger.Binds
import dagger.Module
import ru.altf000.multimodule.collection_list_api.CollectionScreenCreator
import ru.altf000.multimodule.collection_list_impl.data.CollectionListRepositoryImpl
import ru.altf000.multimodule.collection_list_impl.domain.CollectionListRepository
import ru.altf000.multimodule.collection_list_impl.start.CollectionScreenCreatorImpl
import ru.altf000.multimodule.common.di.ScopeScreen

@Module
internal abstract class CollectionListModule {

    @ScopeScreen
    @Binds
    abstract fun provideRepository(collectionRepository: CollectionListRepositoryImpl): CollectionListRepository

    @ScopeScreen
    @Binds
    abstract fun provideScreenCreator(screenCreator: CollectionScreenCreatorImpl): CollectionScreenCreator
}