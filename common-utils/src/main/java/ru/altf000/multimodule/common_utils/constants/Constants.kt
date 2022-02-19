package ru.altf000.multimodule.common_utils.constants

class Constants {

    class Database {
        companion object {
            const val DATABASE_NAME = "appdatabase.db"
            const val TABLE_FULL_CONTENT = "FullContent"
            const val TABLE_GENRES = "Genres"
            const val TABLE_COUNTRIES = "Countries"
            const val TABLE_CONTENT_RECOMMENDATIONS = "ContentRecommendations"
        }
    }

    class Strings {
        companion object {
            const val COMMA = ","
            const val EMPTY_STRING = ""
            const val SPACE = " "
        }
    }

    class Content {
        companion object {
            const val KIND_MOVIE = 1
            const val KIND_SERIAL = 2
            const val BASE_COLLECTION_ID = 4661
        }
    }
}