package ru.altf000.multimodule.common_entities.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FullContent(
    val id: Int = -1,
    val title: String = "",
    val synopsis: String = "",
    val posterUrl: String = "",
    val thumbUrl: String = "",
    val rating: String = "",
    val year: Int = -1,
    val restrict: Int = -1,
    val isSerial: Boolean = false,
    val genres: List<Int> = emptyList(),
    val country: Int = -1,
) : Parcelable