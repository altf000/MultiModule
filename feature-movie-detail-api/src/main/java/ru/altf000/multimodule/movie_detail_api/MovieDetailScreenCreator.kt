package ru.altf000.multimodule.movie_detail_api

import com.github.terrakok.cicerone.Screen
import ru.altf000.multimodule.common_entities.domain.Content

interface MovieDetailScreenCreator {
    fun createScreen(content: Content): Screen
}