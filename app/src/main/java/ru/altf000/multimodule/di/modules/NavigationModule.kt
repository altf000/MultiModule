package ru.altf000.multimodule.di.modules

import dagger.Module
import dagger.Provides
import ru.altf000.multimodule.common.navigation.CustomRouter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Singleton

@Module
internal class NavigationModule {

    @Singleton
    @Provides
    fun provideCicerone(customRouter: CustomRouter): Cicerone<CustomRouter> {
        return Cicerone.create(customRouter)
    }

    @Singleton
    @Provides
    fun provideNavigatorHolder(cicerone: Cicerone<CustomRouter>): NavigatorHolder {
        return cicerone.navigatorHolder
    }
}