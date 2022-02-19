package ru.altf000.multimodule.common_ui.fragment

import android.os.Bundle
import androidx.viewbinding.ViewBinding

interface Bindable {
    val binding: ViewBinding
    fun onBind(savedInstanceState: Bundle?): ViewBinding
}