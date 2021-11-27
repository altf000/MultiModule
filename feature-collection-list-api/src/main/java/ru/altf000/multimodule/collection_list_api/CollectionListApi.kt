package ru.altf000.multimodule.collection_list_api

import ru.altf000.multimodule.module_injector.BaseAPI

interface CollectionListApi : BaseAPI {
    fun getScreenCreator(): CollectionScreenCreator
}