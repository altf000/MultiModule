package ru.altf000.multimodule.common.utils

import ru.altf000.multimodule.common.holder.CountriesHolder
import ru.altf000.multimodule.common.holder.GenresHolder
import ru.altf000.multimodule.common_entities.domain.FullContent

object ContentUtils {

    fun getMeta(content: FullContent): String {
        val result = StringBuffer()
        CountriesHolder.getTitle(content.country)?.let {
            result.append(it)
            result.append(", ")
        }
        for (genreId in content.genres) {
            GenresHolder.getTitle(genreId)?.let {
                result.append(it)
                result.append(", ")
            }
        }
        if (result.length > 2) {
            return result.delete(result.length - 2, result.length).toString()
        }
        return ""
    }
}