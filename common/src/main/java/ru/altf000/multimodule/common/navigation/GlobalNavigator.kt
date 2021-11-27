package ru.altf000.multimodule.common.navigation

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.*
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalNavigator @Inject constructor(
    activity: FragmentActivity,
    fragmentManager: FragmentManager,
    containerId: Int
) : AppNavigator(activity, containerId, fragmentManager) {

    override fun applyCommand(command: Command) {
        when (command) {
            is Forward -> {
                forward(command)
                ScreensChecker.add(command.screen)
            }
            is Replace -> {
                replace(command)
            }
            is BackTo -> {
                backTo(command)
            }
            is Back -> {
                back()
                ScreensChecker.removeLast()
            }
        }
    }

    override fun back() {
        if (localStackCopy.size <= 1) {
            activityBack()
            return
        }
        super.back()
    }
}