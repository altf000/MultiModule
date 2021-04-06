package ru.altf000.multimodule.common_db.dao

import androidx.room.*
import ru.altf000.multimodule.common_entities.entity.FullContentEntity
import ru.altf000.multimodule.constants.Constants

@Dao
interface FullContentDao {

    @Query("SELECT * FROM ${Constants.Database.TABLE_FULL_CONTENT} WHERE id = :id")
    suspend fun findById(id: Int): FullContentEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(content: FullContentEntity)

    @Delete
    suspend fun delete(content: FullContentEntity)
}