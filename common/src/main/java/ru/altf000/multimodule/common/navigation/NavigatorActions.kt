package ru.altf000.multimodule.common.navigation

import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import timber.log.Timber

open class Action

abstract class NavigateAction : Action() {
    abstract fun navigate(navController: NavController)
}

class OpenScreenAction(
    private val directions: NavDirections,
    private val navOptions: NavOptions? = null,
    private val extras: Navigator.Extras? = null,
) : NavigateAction() {
    override fun navigate(navController: NavController) {
        navController.safeNavigate(directions, navOptions, extras)
    }
}

class ExitScreenAction : NavigateAction() {
    override fun navigate(navController: NavController) {
        navController.popBackStack()
    }
}

data class ExitScreenActionTo(
    @IdRes val destinationId: Int,
    private val inclusive: Boolean,
) : NavigateAction() {
    override fun navigate(navController: NavController) {
        navController.popBackStack(destinationId, inclusive)
    }
}

fun NavController.safeNavigate(
    action: NavDirections,
    navOptions: NavOptions? = null,
    extras: Navigator.Extras? = null,
) {
    runCatching {
        navigate(action.actionId, action.arguments, navOptions, extras)
    }.onFailure { ex ->
        Timber.e(ex, action.toString())
    }
}
