package ru.altf000.multimodule.collection_list_impl.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import ru.altf000.multimodule.common.di.ScopeScreen

@Module
class CollectionNavigationModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @ScopeScreen
    @Provides
    fun provideRouter() = cicerone.router

    @ScopeScreen
    @Provides
    fun provideNavigatorHolder() = cicerone.getNavigatorHolder()
}