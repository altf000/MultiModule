package ru.altf000.multimodule.movie_detail_impl.di

import dagger.Binds
import dagger.Module
import ru.altf000.multimodule.common.di.ScopeScreen
import ru.altf000.multimodule.movie_detail_api.MovieDetailScreenCreator
import ru.altf000.multimodule.movie_detail_impl.data.MovieDetailRepositoryImpl
import ru.altf000.multimodule.movie_detail_impl.domain.MovieDetailRepository
import ru.altf000.multimodule.movie_detail_impl.start.MovieDetailScreenCreatorImpl

@Module
internal abstract class MovieDetailModule {

    @ScopeScreen
    @Binds
    abstract fun provideRepository(repository: MovieDetailRepositoryImpl): MovieDetailRepository

    @ScopeScreen
    @Binds
    abstract fun provideScreenCreator(screenCreator: MovieDetailScreenCreatorImpl): MovieDetailScreenCreator
}