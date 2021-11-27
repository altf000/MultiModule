package ru.altf000.multimodule.movie_detail_api

import ru.altf000.multimodule.module_injector.BaseAPI

interface MovieDetailApi : BaseAPI {
    fun getScreenCreator(): MovieDetailScreenCreator
}