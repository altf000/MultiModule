package ru.altf000.multimodule.movie_detail.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.movie_detail.data.MovieDetailRepositoryImpl
import ru.altf000.multimodule.movie_detail.domain.GetContentInfoUseCase
import ru.altf000.multimodule.movie_detail.domain.MovieDetailRepository
import ru.altf000.multimodule.movie_detail.presentation.viewmodel.MovieDetailViewModel

val movieDetailModule = module {
    viewModel { (content: Content) -> MovieDetailViewModel(get(), get(), content) }
    single<MovieDetailRepository> { MovieDetailRepositoryImpl(get(), get()) }
    factory { GetContentInfoUseCase(get()) }
}