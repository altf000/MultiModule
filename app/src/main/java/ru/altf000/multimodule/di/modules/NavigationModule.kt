package ru.altf000.multimodule.di.modules

import com.github.terrakok.cicerone.Cicerone
import dagger.Module
import dagger.Provides
import ru.altf000.multimodule.common.navigation.GlobalRouter
import javax.inject.Singleton

@Module
internal class NavigationModule {

    @Singleton
    @Provides
    fun provideCicerone(globalRouter: GlobalRouter) = Cicerone.create(globalRouter)

    @Singleton
    @Provides
    fun provideNavigatorHolder(cicerone: Cicerone<GlobalRouter>) = cicerone.getNavigatorHolder()
}