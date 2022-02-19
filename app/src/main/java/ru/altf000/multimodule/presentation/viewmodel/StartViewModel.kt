package ru.altf000.multimodule.presentation.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.altf000.multimodule.common.viewmodel.BaseViewModel
import ru.altf000.multimodule.domain.BootloaderUseCase

class StartViewModel(
    private val bootloaderUseCase: BootloaderUseCase
) : BaseViewModel() {

    private val _isLoaded = MutableStateFlow(false)
    val isLoaded = _isLoaded.asStateFlow()

    init {
        launch {
            bootloaderUseCase(Unit).collect { isLoaded ->
                withContext(dispatchersProvider.main) {
                    if (isLoaded) {
                        _isLoaded.value = true
                        navigator.collection(COLLECTION_ID)
                    }
                }
            }
        }
    }

    companion object {
        private const val COLLECTION_ID = 1376
    }
}