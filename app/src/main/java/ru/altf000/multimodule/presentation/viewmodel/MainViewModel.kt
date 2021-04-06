package ru.altf000.multimodule.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.altf000.multimodule.common.viewmodel.BaseViewModel
import ru.altf000.multimodule.domain.MainUseCase

class MainViewModel(
    private val mainUseCase: MainUseCase
) : BaseViewModel() {

    private val _init = MutableLiveData<Boolean>()
    val init: LiveData<Boolean> get() = _init

    init {
        launch {
            mainUseCase
                .execute(Unit)
                .collect {
                    withContext(Dispatchers.Main) {
                        _init.value = it
                    }
                }
        }
    }
}