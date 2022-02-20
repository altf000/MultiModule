package ru.altf000.multimodule.common.providers

import androidx.annotation.StringRes

interface StringsProvider {
    fun getString(@StringRes id: Int, vararg formatArgs: Any): String
}
