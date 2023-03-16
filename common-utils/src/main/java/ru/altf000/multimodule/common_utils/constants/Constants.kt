package ru.altf000.multimodule.common_utils.constants

object Constants {

    object Database {
        const val DATABASE_NAME = "appdatabase.db"
        const val TABLE_FULL_CONTENT = "FullContent"
        const val TABLE_GENRES = "Genres"
        const val TABLE_COUNTRIES = "Countries"
        const val TABLE_CONTENT_RECOMMENDATIONS = "ContentRecommendations"
    }

    object Strings {
        const val COMMA = ","
        const val EMPTY_STRING = ""
        const val SPACE = " "
    }

    object Content {
        const val KIND_MOVIE = 1
        const val KIND_SERIAL = 2
        const val BASE_COLLECTION_ID = 4661
    }
}