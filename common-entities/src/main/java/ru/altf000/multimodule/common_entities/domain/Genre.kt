package ru.altf000.multimodule.common_entities.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val id: Int,
    val title: String,
) : Parcelable