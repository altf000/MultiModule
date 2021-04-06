package ru.altf000.multimodule.common_entities.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.altf000.multimodule.constants.Constants

@Entity(tableName = Constants.Database.TABLE_GENRES)
data class GenreEntity(
    @PrimaryKey val id: Int = -1,
    val title: String = "",
)