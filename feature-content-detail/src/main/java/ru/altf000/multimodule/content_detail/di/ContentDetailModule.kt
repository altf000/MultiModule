package ru.altf000.multimodule.content_detail.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.content_detail.data.ContentDetailRepositoryImpl
import ru.altf000.multimodule.content_detail.domain.GetContentInfoUseCase
import ru.altf000.multimodule.content_detail.domain.ContentDetailRepository
import ru.altf000.multimodule.content_detail.presentation.viewmodel.ContentDetailViewModel

val contentDetailModule = module {
    viewModel { (content: Content) -> ContentDetailViewModel(get(), get(), content) }
    single<ContentDetailRepository> { ContentDetailRepositoryImpl(get(), get()) }
    factory { GetContentInfoUseCase(get()) }
}