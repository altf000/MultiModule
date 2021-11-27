package ru.altf000.multimodule.common_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.altf000.multimodule.common_entities.entity.CountryEntity
import ru.altf000.multimodule.constants.Constants

@Dao
interface CountriesDao {

    @Query("SELECT * FROM ${Constants.Database.TABLE_COUNTRIES}")
    suspend fun getAll(): List<CountryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<CountryEntity>)
}