package ru.altf000.multimodule.collection_list_impl.di

import dagger.Module
import dagger.Provides
import ru.altf000.multimodule.common.di.ScopeScreen
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
class CollectionNavigationModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @ScopeScreen
    @Provides
    fun provideRouter(): Router {
        return cicerone.router
    }

    @ScopeScreen
    @Provides
    fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }
}