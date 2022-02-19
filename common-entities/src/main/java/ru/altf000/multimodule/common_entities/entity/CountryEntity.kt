package ru.altf000.multimodule.common_entities.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.altf000.multimodule.common_utils.constants.Constants

@Entity(tableName = Constants.Database.TABLE_COUNTRIES)
data class CountryEntity(
    @PrimaryKey val id: Int = -1,
    val title: String = "",
    val code: String = ""
)