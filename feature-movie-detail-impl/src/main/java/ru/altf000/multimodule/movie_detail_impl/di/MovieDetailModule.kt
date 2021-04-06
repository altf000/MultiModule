package ru.altf000.multimodule.movie_detail_impl.di

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ru.altf000.multimodule.common.di.ScopeFeature
import ru.altf000.multimodule.common.di.ScopeScreen
import ru.altf000.multimodule.movie_detail_api.MovieDetailScreenCreator
import ru.altf000.multimodule.movie_detail_impl.data.MovieDetailRepositoryImpl
import ru.altf000.multimodule.movie_detail_impl.domain.MovieDetailRepository
import ru.altf000.multimodule.movie_detail_impl.presentation.viewmodel.MovieDetailViewModelFactory
import ru.altf000.multimodule.movie_detail_impl.start.MovieDetailScreenCreatorImpl

@Module
internal class MovieDetailModule {

    @ScopeScreen
    @Provides
    fun provideRepository(repository: MovieDetailRepositoryImpl): MovieDetailRepository {
        return repository
    }

    @ScopeScreen
    @Provides
    fun provideScreenCreator(screenCreator: MovieDetailScreenCreatorImpl): MovieDetailScreenCreator {
        return screenCreator
    }

    @ScopeScreen
    @Provides
    fun provideViewModelFactory(factory: MovieDetailViewModelFactory): ViewModelProvider.Factory {
        return factory
    }
}