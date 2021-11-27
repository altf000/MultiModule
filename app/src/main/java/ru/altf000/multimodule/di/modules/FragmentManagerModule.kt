package ru.altf000.multimodule.di.modules

import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class FragmentManagerModule(
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) {

    @Provides
    fun provideContainerId(): Int = containerId

    @Provides
    @Singleton
    fun provideFragmentManager(): FragmentManager = fragmentManager
}