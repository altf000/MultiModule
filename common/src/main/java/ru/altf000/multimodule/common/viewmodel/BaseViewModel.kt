package ru.altf000.multimodule.common.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.altf000.multimodule.common.navigation.Navigator
import ru.altf000.multimodule.common.providers.DispatchersProvider
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope, KoinComponent {

    override val coroutineContext: CoroutineContext by lazy { SupervisorJob() + Dispatchers.IO }

    protected val navigator by inject<Navigator>()
    protected val dispatchersProvider by inject<DispatchersProvider>()

    @CallSuper
    override fun onCleared() {
        cancelChildrenJobs()
        super.onCleared()
    }

    private fun cancelChildrenJobs() = coroutineContext.cancelChildren()
}