package ru.altf000.multimodule.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.altf000.multimodule.collection_list_api.CollectionListApi
import ru.altf000.multimodule.collection_list_impl.di.CollectionListComponentHolder
import ru.altf000.multimodule.collection_list_impl.di.CollectionListDependencies
import ru.altf000.multimodule.common_db.di.DatabaseComponent
import ru.altf000.multimodule.common_network.di.NetworkComponent
import ru.altf000.multimodule.di.app.MainActivityComponent
import ru.altf000.multimodule.feature_recommendation_api.RecommendationsApi
import ru.altf000.multimodule.feature_recommendation_impl.di.RecommendationsComponentHolder
import ru.altf000.multimodule.feature_recommendation_impl.di.RecommendationsDependencies
import ru.altf000.multimodule.movie_detail_api.MovieDetailApi
import ru.altf000.multimodule.movie_detail_impl.di.MovieDetailComponentHolder
import ru.altf000.multimodule.movie_detail_impl.di.MovieDetailDependencies
import javax.inject.Singleton

@Module
internal class FeatureModule {

    @Singleton
    @Provides
    fun provideFeatureCollectionListDependencies() = object : CollectionListDependencies {
        override fun getApiService() = NetworkComponent.get().apiService()
        override fun getRouter() = MainActivityComponent.get().globalRouter()
    }

    @Provides
    fun provideFeatureCollectionList(dependencies: CollectionListDependencies): CollectionListApi {
        CollectionListComponentHolder.init(dependencies)
        return CollectionListComponentHolder.get()
    }

    @Singleton
    @Provides
    fun provideFeatureMovieDetailDependencies(
        context: Context,
        recommendationDependencies: RecommendationsDependencies
    ) = object : MovieDetailDependencies {
        override fun getApiService() = NetworkComponent.get().apiService()
        override fun getDatabase() = DatabaseComponent.get(context).getDatabase()
        override fun getRouter() = MainActivityComponent.get().globalRouter()
        override fun getRecommendationsApi(): RecommendationsApi {
            RecommendationsComponentHolder.init(recommendationDependencies)
            return RecommendationsComponentHolder.get()
        }
    }

    @Provides
    fun provideFeatureMovieDetail(dependencies: MovieDetailDependencies): MovieDetailApi {
        MovieDetailComponentHolder.init(dependencies)
        return MovieDetailComponentHolder.get()
    }

    @Singleton
    @Provides
    fun provideRecommendationDependencies(context: Context) = object : RecommendationsDependencies {
        override fun getApiService() = NetworkComponent.get().apiService()
        override fun getDatabase() = DatabaseComponent.get(context).getDatabase()
    }
}