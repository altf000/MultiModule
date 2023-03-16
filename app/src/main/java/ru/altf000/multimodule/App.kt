package ru.altf000.multimodule

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.altf000.multimodule.feature_collection_list.di.collectionListModule
import ru.altf000.multimodule.common.di.commonModule
import ru.altf000.multimodule.common_db.di.dbModule
import ru.altf000.multimodule.common_network.di.networkModule
import ru.altf000.multimodule.feature_content_detail.di.contentDetailModule
import ru.altf000.multimodule.di.appModule
import ru.altf000.multimodule.feature_collections.presentation.di.collectionsModule
import ru.altf000.multimodule.feature_recommendation_impl.di.recommendationsModule
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                dbModule,
                networkModule,
                collectionListModule,
                collectionsModule,
                contentDetailModule,
                recommendationsModule,
                commonModule
            )
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}