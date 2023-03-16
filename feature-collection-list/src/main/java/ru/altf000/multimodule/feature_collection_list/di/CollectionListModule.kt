package ru.altf000.multimodule.feature_collection_list.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.altf000.multimodule.feature_collection_list.data.CollectionListRepositoryImpl
import ru.altf000.multimodule.feature_collection_list.domain.CollectionListRepository
import ru.altf000.multimodule.feature_collection_list.domain.GetCollectionListUseCase
import ru.altf000.multimodule.feature_collection_list.presentation.viewmodel.CollectionListViewModel

val collectionListModule = module {
    viewModel { (collectionId: Int) -> CollectionListViewModel(get(), collectionId) }
    single<CollectionListRepository> { CollectionListRepositoryImpl(get()) }
    factory { GetCollectionListUseCase(get()) }
}