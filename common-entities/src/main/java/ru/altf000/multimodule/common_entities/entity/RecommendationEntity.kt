package ru.altf000.multimodule.common_entities.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.altf000.multimodule.common_utils.constants.Constants

@Entity(tableName = Constants.Database.TABLE_CONTENT_RECOMMENDATIONS)
data class RecommendationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val parentId: Int = -1,
    val contentId: Int = -1,
    val title: String = "",
    val synopsis: String = "",
    val posterUrl: String = "",
    val rating: String = "",
    val year: Int = -1,
    val restrict: Int = -1,
    val isSerial: Boolean = false
)