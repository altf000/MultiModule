package ru.altf000.multimodule.common.navigation

import kotlinx.coroutines.flow.Flow
import ru.altf000.multimodule.common_entities.domain.Content

interface Navigator {
    val navigateActions: Flow<NavigateAction?>
    fun navigate(action: NavigateAction)
    fun main()
    fun movieDetail(item: Content)
}
