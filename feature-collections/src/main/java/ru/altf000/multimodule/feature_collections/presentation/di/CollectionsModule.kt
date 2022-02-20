package ru.altf000.multimodule.feature_collections.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.altf000.multimodule.feature_collections.presentation.data.CollectionsRepositoryImpl
import ru.altf000.multimodule.feature_collections.presentation.domain.CollectionsRepository
import ru.altf000.multimodule.feature_collections.presentation.domain.GetCollectionsUseCase
import ru.altf000.multimodule.feature_collections.presentation.viewmodel.CollectionsViewModel

val collectionsModule = module {
    viewModel { CollectionsViewModel(get()) }
    single<CollectionsRepository> { CollectionsRepositoryImpl(get()) }
    factory { GetCollectionsUseCase(get()) }
}