package ru.altf000.multimodule.di.app

import dagger.Component
import dagger.internal.Preconditions
import ru.altf000.multimodule.common.navigation.GlobalRouter
import ru.altf000.multimodule.di.modules.*
import ru.altf000.multimodule.presentation.view.MainActivity
import javax.inject.Singleton

@Component(
    modules = [
        FeatureModule::class,
        NavigationModule::class,
        FragmentManagerModule::class,
        ActivityModule::class,
        RepositoryModule::class,
        MainActivityModule::class
    ]
)
@Singleton
internal interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun globalRouter(): GlobalRouter

    companion object {

        @Volatile
        private var instance: MainActivityComponent? = null

        fun get(): MainActivityComponent {
            return Preconditions.checkNotNull(
                instance,
                "AppComponent is not initialized yet. Call init first."
            )!!
        }

        fun init(component: MainActivityComponent) {
            require(instance == null) { "AppComponent is already initialized." }
            instance = component
        }

        fun release() {
            instance = null
        }
    }
}