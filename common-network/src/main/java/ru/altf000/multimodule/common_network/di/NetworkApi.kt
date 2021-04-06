package ru.altf000.multimodule.common_network.di

import ru.altf000.multimodule.common_network.network.api.ApiService
import ru.altf000.multimodule.module_injector.BaseAPI

interface NetworkApi : BaseAPI {

    fun apiService(): ApiService
}