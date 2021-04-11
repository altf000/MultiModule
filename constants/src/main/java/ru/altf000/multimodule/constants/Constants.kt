package ru.altf000.multimodule.constants

class Constants {

    class Database {
        companion object {
            const val DATABASE_NAME = "appdatabase.db"
            const val TABLE_FULL_CONTENT = "FullContent"
            const val TABLE_GENRES = "Genres"
            const val TABLE_COUNTRIES = "Countries"
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
        }
    }
}