package ru.altf000.multimodule.common_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.altf000.multimodule.common_entities.entity.RecommendationEntity
import ru.altf000.multimodule.constants.Constants

@Dao
interface RecommendationsDao {

    @Query("SELECT * FROM ${Constants.Database.TABLE_CONTENT_RECOMMENDATIONS} WHERE parentId = :parentId")
    suspend fun getRecommendations(parentId: Int): List<RecommendationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<RecommendationEntity>)

    @Query("DELETE FROM ${Constants.Database.TABLE_CONTENT_RECOMMENDATIONS} WHERE parentId = :parentId")
    suspend fun deleteAll(parentId: Int)
}