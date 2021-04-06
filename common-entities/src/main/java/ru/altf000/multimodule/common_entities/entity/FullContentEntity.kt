package ru.altf000.multimodule.common_entities.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.altf000.multimodule.constants.Constants

@Entity(tableName = Constants.Database.TABLE_FULL_CONTENT)
data class FullContentEntity(
    @PrimaryKey val id: Int = -1,
    val title: String = "",
    val synopsis: String = "",
    val posterUrl: String = "",
    val thumbUrl: String = "",
    val rating: String = "",
    val year: Int = -1,
    val restrict: Int = -1,
    val isSerial: Boolean = false,
    val genres: List<Int> = emptyList(),
    val country: Int = -1
)