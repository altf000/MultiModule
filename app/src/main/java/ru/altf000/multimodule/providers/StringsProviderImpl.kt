package ru.altf000.multimodule.providers

import android.content.Context
import ru.altf000.multimodule.common.providers.StringsProvider

internal class StringsProviderImpl(private val context: Context) : StringsProvider {
    override fun getString(id: Int, vararg formatArgs: Any): String = context.getString(id, *formatArgs)
}