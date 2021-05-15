package ru.altf000.multimodule.di.modules

import dagger.Module
import dagger.Provides
import ru.altf000.multimodule.common.navigation.GlobalRouter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Singleton

@Module
internal class NavigationModule {

    @Singleton
    @Provides
    fun provideCicerone(globalRouter: GlobalRouter): Cicerone<GlobalRouter> {
        return Cicerone.create(globalRouter)
    }

    @Singleton
    @Provides
    fun provideNavigatorHolder(cicerone: Cicerone<GlobalRouter>): NavigatorHolder {
        return cicerone.navigatorHolder
    }
}