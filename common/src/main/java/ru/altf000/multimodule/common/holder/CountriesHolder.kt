package ru.altf000.multimodule.common.holder

import ru.altf000.multimodule.common_entities.server.countries.Country
import java.util.*

object CountriesHolder {

    private val COUNTRIES = mutableListOf<Country>()

    fun getCountries(): List<Country> {
        return Collections.unmodifiableList(COUNTRIES)
    }

    fun saveCountries(genresList: List<Country>) {
        COUNTRIES.clear()
        COUNTRIES.addAll(genresList)
    }

    fun getTitle(countryId: Int): String? {
        return COUNTRIES.find { it.id == countryId }?.title
    }
}