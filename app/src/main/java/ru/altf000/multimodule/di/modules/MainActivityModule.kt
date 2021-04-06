package ru.altf000.multimodule.di.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ru.altf000.multimodule.presentation.viewmodel.MainViewModelFactory
import javax.inject.Singleton

@Module
internal class MainActivityModule {

    @Singleton
    @Provides
    fun provideViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory {
        return factory
    }
}