package ru.altf000.multimodule.common_entities.converters

import androidx.room.TypeConverter

class ListToIntConverter {

    @TypeConverter
    fun fromString(stringIntString: String): List<Int> {
        return stringIntString.split(",").map { it.toInt() }
    }

    @TypeConverter
    fun toString(stringList: List<Int>): String {
        return stringList.joinToString(separator = ",")
    }
}