package ru.altf000.multimodule.movie_detail_api

import ru.altf000.multimodule.common_entities.domain.Content
import ru.terrakok.cicerone.Screen

interface MovieDetailScreenCreator {

    fun createScreen(content: Content): Screen
}