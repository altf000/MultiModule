package ru.altf000.multimodule.common_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.altf000.multimodule.common_entities.entity.GenreEntity
import ru.altf000.multimodule.common_utils.constants.Constants

@Dao
interface GenresDao {

    @Query("SELECT * FROM ${Constants.Database.TABLE_GENRES}")
    suspend fun getAll(): List<GenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(genres: List<GenreEntity>)
}