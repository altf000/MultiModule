package ru.altf000.multimodule.common.navigation

import com.github.terrakok.cicerone.Screen

object ScreensChecker {

    private val SCREENS = mutableListOf<Screen>()

    fun add(screen: Screen) {
        SCREENS.add(screen)
    }

    fun removeLast() {
        if (SCREENS.isNotEmpty()) {
            SCREENS.removeAt(SCREENS.size - 1)
        }
    }

    fun hasScreen(key: String) = SCREENS.find { it.screenKey == key } != null
}