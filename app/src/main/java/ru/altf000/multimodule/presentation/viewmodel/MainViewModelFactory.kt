package ru.altf000.multimodule.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.altf000.multimodule.domain.MainUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
internal class MainViewModelFactory @Inject constructor(
    private val mainUseCase: MainUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>) = MainViewModel(mainUseCase) as T
}