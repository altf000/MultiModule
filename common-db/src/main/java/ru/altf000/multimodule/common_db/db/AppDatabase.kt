package ru.altf000.multimodule.common_db.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.altf000.multimodule.common_db.dao.CountriesDao
import ru.altf000.multimodule.common_db.dao.FullContentDao
import ru.altf000.multimodule.common_db.dao.GenresDao
import ru.altf000.multimodule.common_db.dao.RecommendationsDao
import ru.altf000.multimodule.common_entities.converters.ListToIntConverter
import ru.altf000.multimodule.common_entities.entity.CountryEntity
import ru.altf000.multimodule.common_entities.entity.FullContentEntity
import ru.altf000.multimodule.common_entities.entity.GenreEntity
import ru.altf000.multimodule.common_entities.entity.RecommendationEntity
import ru.altf000.multimodule.constants.Constants

@Database(
    entities = [
        FullContentEntity::class,
        GenreEntity::class,
        CountryEntity::class,
        RecommendationEntity::class
    ],
    version = 1
)
@TypeConverters(ListToIntConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun fullContentDao(): FullContentDao

    abstract fun genresDao(): GenresDao

    abstract fun countriesDao(): CountriesDao

    abstract fun recommendationsDao(): RecommendationsDao

    companion object {

        private var database: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (database == null) {
                synchronized(AppDatabase::class.java) {
                    if (database == null) {
                        database = Room
                            .databaseBuilder(
                                context.applicationContext,
                                AppDatabase::class.java, Constants.Database.DATABASE_NAME
                            )
                            .build()
                    }
                    return database!!
                }
            }
            return database!!
        }
    }
}