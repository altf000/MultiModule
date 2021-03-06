package ru.altf000.multimodule.common.providers

import ru.altf000.multimodule.common_entities.domain.FullContent

interface ContentMetaInfoProvider {
    fun getMeta(content: FullContent): String
}