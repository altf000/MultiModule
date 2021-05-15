package ru.altf000.multimodule.common.navigation

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalNavigator @Inject constructor(
    activity: FragmentActivity,
    fragmentManager: FragmentManager,
    containerId: Int
) : SupportAppNavigator(activity, fragmentManager, containerId) {

    override fun applyCommand(command: Command) {
        when (command) {
            is Forward -> {
                activityForward(command)
                ScreensChecker.add(command.screen)
            }
            is Replace -> {
                activityReplace(command)
            }
            is BackTo -> {
                backTo(command)
            }
            is Back -> {
                fragmentBack()
                ScreensChecker.removeLast()
            }
        }
    }

    override fun fragmentBack() {
        if (localStackCopy.size <= 1) {
            activityBack()
            return
        }
        super.fragmentBack()
    }
}