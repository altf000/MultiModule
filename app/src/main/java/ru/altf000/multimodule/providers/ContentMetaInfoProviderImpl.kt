package ru.altf000.multimodule.providers

import ru.altf000.multimodule.common.datasource.CountriesReadOnlyDataSource
import ru.altf000.multimodule.common.datasource.GenresDataSource
import ru.altf000.multimodule.common.providers.ContentMetaInfoProvider
import ru.altf000.multimodule.common_entities.domain.Country
import ru.altf000.multimodule.common_entities.domain.FullContent
import ru.altf000.multimodule.common_entities.domain.Genre
import ru.altf000.multimodule.common_utils.constants.Constants

class ContentMetaInfoProviderImpl(
    private val countriesDataSource: CountriesReadOnlyDataSource,
    private val genresDataSource: GenresDataSource,
) : ContentMetaInfoProvider {

    override fun getMeta(content: FullContent): String {
        val result = StringBuffer()
        countriesDataSource.data.value.getCountryTitle(content.country)?.let { title ->
            with(result) {
                append(title)
                append(Constants.Strings.COMMA)
                append(Constants.Strings.SPACE)
            }
        }
        for (genreId in content.genres) {
            genresDataSource.data.value.getGenreTitle(genreId)?.let { title ->
                with(result) {
                    append(title)
                    append(Constants.Strings.COMMA)
                    append(Constants.Strings.SPACE)
                }
            }
        }
        if (result.length > 2) {
            return result.substring(0, result.length - 2)
        }
        return Constants.Strings.SPACE
    }

    private fun List<Country>.getCountryTitle(countryId: Int) = find { it.id == countryId }?.title

    private fun List<Genre>.getGenreTitle(genreId: Int) = find { it.id == genreId }?.title
}