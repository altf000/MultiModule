package ru.altf000.multimodule.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.altf000.multimodule.common.navigation.Navigator
import ru.altf000.multimodule.common.providers.DispatchersProvider
import ru.altf000.multimodule.common.providers.MetaInfoProvider
import ru.altf000.multimodule.data.BootloaderRepositoryImpl
import ru.altf000.multimodule.domain.BootloaderRepository
import ru.altf000.multimodule.domain.BootloaderUseCase
import ru.altf000.multimodule.navigator.NavigatorImpl
import ru.altf000.multimodule.presentation.viewmodel.StartViewModel
import ru.altf000.multimodule.providers.DispatchersProviderImpl
import ru.altf000.multimodule.providers.MetaInfoProviderImpl

val appModule = module {
    viewModel { StartViewModel(get()) }
    single<Navigator> { NavigatorImpl() }
    single<BootloaderRepository> { BootloaderRepositoryImpl(get(), get(), get(), get(), get(), get()) }
    factory { BootloaderUseCase(get()) }
    single<DispatchersProvider> { DispatchersProviderImpl() }
    single<MetaInfoProvider> { MetaInfoProviderImpl(get(), get()) }
}
