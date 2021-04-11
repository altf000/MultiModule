package ru.altf000.multimodule.common_ui.fragment

import androidx.fragment.app.Fragment

abstract class BaseFragment<T> : Fragment() {

    public var _binding: T? = null
    public val binding get() = _binding!!

    override fun onStart() {
        super.onStart()
        if (!isHidden) {
            onStartInner()
        }
    }

    override fun onStop() {
        super.onStop()
        if (!isHidden) {
            onStopInner()
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            onStopInner()
        } else {
            onStartInner()
        }
    }

    abstract fun onStartInner()

    abstract fun onStopInner()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}