package ru.altf000.multimodule.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.altf000.multimodule.collection_list_api.CollectionListApi
import ru.altf000.multimodule.collection_list_impl.di.CollectionListComponentHolder
import ru.altf000.multimodule.collection_list_impl.di.CollectionListDependencies
import ru.altf000.multimodule.common.navigation.CustomRouter
import ru.altf000.multimodule.common_db.db.AppDatabase
import ru.altf000.multimodule.common_db.di.DatabaseComponent
import ru.altf000.multimodule.common_network.di.NetworkComponent
import ru.altf000.multimodule.common_network.network.api.ApiService
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
    fun provideFeatureCollectionListDependencies(): CollectionListDependencies {
        return object : CollectionListDependencies {

            override fun getApiService(): ApiService = NetworkComponent.get().apiService()

            override fun getRouter(): CustomRouter = MainActivityComponent.get().globalRouter()
        }
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
    ): MovieDetailDependencies {
        return object : MovieDetailDependencies {

            override fun getApiService(): ApiService = NetworkComponent.get().apiService()

            override fun getDatabase(): AppDatabase = DatabaseComponent.get(context).getDatabase()

            override fun getRouter(): CustomRouter = MainActivityComponent.get().globalRouter()

            override fun getRecommendationsApi(): RecommendationsApi {
                RecommendationsComponentHolder.init(recommendationDependencies)
                return RecommendationsComponentHolder.get()
            }
        }
    }

    @Provides
    fun provideFeatureMovieDetail(dependencies: MovieDetailDependencies): MovieDetailApi {
        MovieDetailComponentHolder.init(dependencies)
        return MovieDetailComponentHolder.get()
    }

    @Singleton
    @Provides
    fun provideRecommendationDependencies(): RecommendationsDependencies {
        return object : RecommendationsDependencies {

            override fun getApiService(): ApiService = NetworkComponent.get().apiService()
        }
    }
}