package ru.altf000.multimodule.common.utils

import ru.altf000.multimodule.common.holder.CountriesHolder
import ru.altf000.multimodule.common.holder.GenresHolder
import ru.altf000.multimodule.common_entities.domain.FullContent
import ru.altf000.multimodule.constants.Constants

object ContentUtils {

    fun getMeta(content: FullContent): String {
        val result = StringBuffer()
        CountriesHolder.getTitle(content.country)?.let {
            result.append(it)
            result.append(Constants.Strings.COMMA)
            result.append(Constants.Strings.SPACE)
        }
        for (genreId in content.genres) {
            GenresHolder.getTitle(genreId)?.let {
                result.append(it)
                result.append(Constants.Strings.COMMA)
                result.append(Constants.Strings.SPACE)
            }
        }
        if (result.length > 2) {
            return result.substring(0, result.length - 2)
        }
        return Constants.Strings.SPACE
    }
}