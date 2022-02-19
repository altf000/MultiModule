package ru.altf000.multimodule.data

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import ru.altf000.multimodule.common.datasource.CountriesReadOnlyDataSource
import ru.altf000.multimodule.common.datasource.CountriesUpdatableDataSource
import ru.altf000.multimodule.common.datasource.GenresReadOnlyDataSource
import ru.altf000.multimodule.common.datasource.GenresUpdatableDataSource
import ru.altf000.multimodule.common_db.db.AppDatabase
import ru.altf000.multimodule.common_entities.mapper.toDomain
import ru.altf000.multimodule.common_entities.mapper.toEntity
import ru.altf000.multimodule.common_entities.network.categories.GenreNetwork
import ru.altf000.multimodule.common_entities.network.countries.CountryNetwork
import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import ru.altf000.multimodule.common_network.network.api.ApiService
import ru.altf000.multimodule.domain.BootloaderRepository

internal class BootloaderRepositoryImpl(
    private val apiService: ApiService,
    private val database: AppDatabase,
    private val genresDataSource: GenresReadOnlyDataSource,
    private val genresWritableDataSource: GenresUpdatableDataSource,
    private val countryDataSource: CountriesReadOnlyDataSource,
    private val countryWritableDataSource: CountriesUpdatableDataSource,
) : BootloaderRepository {

    override fun load() = flow {

        if (genresDataSource.data.value.isEmpty()) {
            val dao = database.genresDao()
            val cachedGenres = dao.getAll()
            if (cachedGenres.isEmpty()) {
                val categoriesResult = apiService.getCategories()
                if (categoriesResult is RequestResult.Success) {
                    val categories = categoriesResult.value.result
                    val genresList = mutableListOf<GenreNetwork>()
                    for (category in categories) {
                        for (genre in category.genres) {
                            genresList.add(genre)
                        }
                    }
                    dao.insertAll(genresList.map { it.toEntity() })
                    genresWritableDataSource.update(genresList.map { it.toDomain() })
                }
            } else {
                genresWritableDataSource.update(cachedGenres.map { it.toDomain() })
            }
        }

        if (countryDataSource.data.value.isEmpty()) {
            val dao = database.countriesDao()
            val cachedCountries = dao.getAll()
            if (cachedCountries.isEmpty()) {
                val countriesResult = apiService.getCountries()
                if (countriesResult is RequestResult.Success) {
                    val countriesMap = countriesResult.value.result
                    val countriesList = countriesMap.map {
                        CountryNetwork(it.key.toInt(), it.value.title, it.value.code)
                    }
                    dao.insertAll(countriesList.map { it.toEntity() })
                    countryWritableDataSource.update(countriesList.map { it.toDomain() })
                }
            } else {
                countryWritableDataSource.update(cachedCountries.map { it.toDomain() })
            }
        }
        
        emit(true)
    }
}
