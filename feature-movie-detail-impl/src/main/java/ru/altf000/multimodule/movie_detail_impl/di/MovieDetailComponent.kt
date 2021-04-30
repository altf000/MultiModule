package ru.altf000.multimodule.movie_detail_impl.di

import dagger.Component
import ru.altf000.multimodule.common.di.ScopeScreen
import ru.altf000.multimodule.movie_detail_api.MovieDetailApi
import ru.altf000.multimodule.movie_detail_impl.presentation.view.MovieDetailFragment

@Component(
    modules = [MovieDetailModule::class, MovieDetailModule::class],
    dependencies = [MovieDetailDependencies::class]
)
@ScopeScreen
internal abstract class MovieDetailComponent : MovieDetailApi {

    internal abstract fun inject(fragment: MovieDetailFragment)
}

