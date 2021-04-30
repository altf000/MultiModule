package ru.altf000.multimodule.presentation.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.altf000.multimodule.common.viewmodel.BaseViewModel
import ru.altf000.multimodule.domain.MainUseCase

class MainViewModel(
    private val mainUseCase: MainUseCase
) : BaseViewModel() {

    private val _initFlow = MutableStateFlow(false)
    val initFlow: StateFlow<Boolean> = _initFlow.asStateFlow()

    init {
        launch {
            mainUseCase
                .execute(Unit)
                .collect {
                    withContext(Dispatchers.Main) {
                        _initFlow.value = true
                    }
                }
        }
    }
}