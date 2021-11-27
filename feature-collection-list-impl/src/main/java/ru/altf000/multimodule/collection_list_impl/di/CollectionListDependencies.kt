package ru.altf000.multimodule.collection_list_impl.di

import ru.altf000.multimodule.common.navigation.GlobalRouter
import ru.altf000.multimodule.common_network.network.api.ApiService
import ru.altf000.multimodule.module_injector.BaseDependencies

interface CollectionListDependencies : BaseDependencies {
    fun getApiService(): ApiService
    fun getRouter(): GlobalRouter
}