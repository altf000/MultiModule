package ru.altf000.multimodule.common.navigation

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.manager.SupportRequestManagerFragment
import ru.altf000.multimodule.common.utils.findFromIndexReverse
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

typealias ExcludedFragment = SupportRequestManagerFragment

@Singleton
class CustomNavigator @Inject constructor(
    activity: FragmentActivity,
    fragmentManager: FragmentManager,
    containerId: Int
) : SupportAppNavigator(activity, fragmentManager, containerId) {

    init {
        localStackCopy = LinkedList()
    }

    override fun applyCommands(commands: Array<Command>) {

        fragmentManager.executePendingTransactions()

        for (command in commands) {
            try {
                applyCommand(command)
            } catch (e: RuntimeException) {
                errorOnApplyCommand(command, e)
            }
        }
    }

    override fun applyCommand(command: Command) {
        when (command) {
            is Add -> {
                activityAdd(command)
            }
            is Forward -> {
                throw (Exception("Command Forward not supported"))
            }
            is Replace -> {
                throw (Exception("Command Replace not supported"))
            }
            is BackTo -> {
                backTo(command)
            }
            is Back -> {
                fragmentBack()
            }
        }
    }

    private fun activityAdd(command: Add) {

        val screen = command.screen as SupportAppScreen
        val activityIntent = screen.getActivityIntent(activity)

        if (activityIntent != null) {
            val options = createStartActivityOptions(command, activityIntent)
            activity.startActivity(activityIntent, options)
        } else {
            fragmentAdd(command)
        }
    }

    private fun fragmentAdd(command: Add) {

        val fragmentTransaction = fragmentManager.beginTransaction()

        val fragments = fragmentManager.fragments
        fragments.findLast { it !is ExcludedFragment }?.let {
            fragmentTransaction.hide(it)
        }

        val screen = command.screen as SupportAppScreen
        createFragment(screen)?.let {
            fragmentTransaction.add(containerId, it, screen.screenKey)
            ScreensChecker.add(screen)
        }

        fragmentTransaction.commit()
    }

    override fun fragmentBack() {

        val size = fragmentManager.fragments.count { it !is ExcludedFragment }
        if (size <= 1) {
            activityBack()
            return
        }

        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragments = fragmentManager.fragments

        fragments.findLast { it !is SupportRequestManagerFragment }?.let { current ->

            fragmentTransaction.remove(current)
            ScreensChecker.removeLast()

            val index = fragments.indexOf(current)
            if (index != -1) {
                fragments.findFromIndexReverse(index - 1) { it !is ExcludedFragment }?.let { prev ->
                    fragmentTransaction.show(prev)
                }
            }
        }

        fragmentTransaction.commit()
    }
}