package ru.altf000.multimodule.common.holder

import ru.altf000.multimodule.common_entities.server.categories.Genre
import java.util.*

object GenresHolder {

    private val GENRES = mutableListOf<Genre>()

    fun getGenres(): List<Genre> {
        return Collections.unmodifiableList(GENRES)
    }

    fun saveGenres(genresList: List<Genre>) {
        GENRES.clear()
        GENRES.addAll(genresList)
    }

    fun getTitle(genreId: Int): String? {
        return GENRES.find { it.id == genreId }?.title
    }
}