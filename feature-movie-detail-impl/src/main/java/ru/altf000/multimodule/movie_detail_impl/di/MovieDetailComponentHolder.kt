package ru.altf000.multimodule.movie_detail_impl.di

import ru.altf000.multimodule.module_injector.ComponentHolder
import ru.altf000.multimodule.movie_detail_api.MovieDetailApi

object MovieDetailComponentHolder : ComponentHolder<MovieDetailApi, MovieDetailDependencies> {

    private var component: MovieDetailComponent? = null

    override fun init(dependencies: MovieDetailDependencies) {
        if (component == null) {
            synchronized(MovieDetailComponentHolder::class.java) {
                if (component == null) {
                    component = DaggerMovieDetailComponent
                        .builder()
                        .movieDetailDependencies(dependencies)
                        .build()
                }
            }
        }
    }

    override fun get(): MovieDetailApi = getComponent()

    internal fun getComponent(): MovieDetailComponent {
        return component!!
    }

    override fun reset() {
        component = null
    }
}