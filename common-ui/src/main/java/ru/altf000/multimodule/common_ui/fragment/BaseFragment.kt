package ru.altf000.multimodule.common_ui.fragment

import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment<T> : Fragment() {

    protected var _binding: T? = null
    protected val binding get() = _binding!!

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

    open fun onStartInner() {}

    open fun onStopInner() {}

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun showToast(stringRes: Int) {
        Toast.makeText(
            context,
            resources.getString(stringRes),
            Toast.LENGTH_LONG
        ).show()
    }
}