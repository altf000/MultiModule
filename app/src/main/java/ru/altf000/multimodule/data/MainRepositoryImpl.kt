package ru.altf000.multimodule.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.altf000.multimodule.common.holder.CountriesHolder
import ru.altf000.multimodule.common.holder.GenresHolder
import ru.altf000.multimodule.common_db.db.AppDatabase
import ru.altf000.multimodule.common_entities.entity.CountryEntity
import ru.altf000.multimodule.common_entities.entity.GenreEntity
import ru.altf000.multimodule.common_entities.server.categories.Genre
import ru.altf000.multimodule.common_entities.server.countries.Country
import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import ru.altf000.multimodule.common_network.network.api.ApiService
import ru.altf000.multimodule.domain.MainRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val database: AppDatabase
) : MainRepository {

    override fun loadRequireData(): Flow<Boolean> = flow {

        if (GenresHolder.getGenres().isEmpty()) {
            val dao = database.genresDao()
            val cachedGenres = dao.getAll()
            if (cachedGenres.isEmpty()) {
                val categoriesResult = apiService.getCategories()
                if (categoriesResult is RequestResult.Success) {
                    val categories = categoriesResult.value.result
                    val genresList = mutableListOf<Genre>()
                    for (category in categories) {
                        for (genre in category.genres) {
                            genresList.add(genre)
                        }
                    }
                    dao.insertAll(genresList.map { it.toEntity() })
                    GenresHolder.saveGenres(genresList)
                }
            } else {
                GenresHolder.saveGenres(cachedGenres.map { it.toLocal() })
            }
        }

        if (CountriesHolder.getCountries().isEmpty()) {
            val dao = database.countriesDao()
            val cachedCountries = dao.getAll()
            if (cachedCountries.isEmpty()) {
                val countriesResult = apiService.getCountries()
                if (countriesResult is RequestResult.Success) {
                    val countriesMap = countriesResult.value.result
                    val countriesList = countriesMap.map {
                        Country(it.key.toInt(), it.value.title, it.value.code)
                    }
                    dao.insertAll(countriesList.map { it.toEntity() })
                    CountriesHolder.saveCountries(countriesList)
                }
            } else {
                CountriesHolder.saveCountries(cachedCountries.map { it.toLocal() })
            }
        }

        emit(true)
    }
}

private fun GenreEntity.toLocal() = Genre(this.id, this.title)

private fun Genre.toEntity() = GenreEntity(this.id, this.title.orEmpty())

private fun Country.toEntity() = CountryEntity(this.id, this.title.orEmpty(), this.code.orEmpty())

private fun CountryEntity.toLocal() = Country(this.id, this.title, this.code)