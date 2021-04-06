package ru.altf000.multimodule.common_network.di

import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
abstract class NetworkComponent : NetworkApi {

    companion object {

        @Volatile
        private var networkComponent: NetworkComponent? = null

        fun get(): NetworkComponent {
            if (networkComponent == null) {
                synchronized(NetworkComponent::class.java) {
                    if (networkComponent == null) {
                        networkComponent = DaggerNetworkComponent
                            .builder()
                            .build()
                    }
                }
            }
            return networkComponent!!
        }
    }
}