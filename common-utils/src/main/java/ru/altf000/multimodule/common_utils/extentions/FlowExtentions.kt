package ru.altf000.multimodule.common_utils.extentions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

fun LifecycleOwner.collectOnCreated(block: suspend () -> Unit) {
    collectLifecycle(Lifecycle.State.CREATED, block)
}

fun LifecycleOwner.collectOnStarted(block: suspend () -> Unit) {
    collectLifecycle(Lifecycle.State.STARTED, block)
}

fun LifecycleOwner.collectLifecycle(state: Lifecycle.State, block: suspend () -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(state) {
            block()
        }
    }
}

fun <T> Flow<T>.collectOnCreated(
    viewLifecycleOwner: LifecycleOwner,
    cancelBlock: (() -> Unit)? = null,
    block: suspend (T) -> Unit,
) {
    collectLifecycle(viewLifecycleOwner, Lifecycle.State.CREATED, cancelBlock, block)
}

fun <T> Flow<T>.collectOnStarted(
    viewLifecycleOwner: LifecycleOwner,
    cancelBlock: (() -> Unit)? = null,
    block: suspend (T) -> Unit
) {
    collectLifecycle(viewLifecycleOwner, Lifecycle.State.STARTED, cancelBlock, block)
}

private fun <T> Flow<T>.collectLifecycle(
    viewLifecycleOwner: LifecycleOwner,
    state: Lifecycle.State,
    cancelBlock: (() -> Unit)? = null,
    block: suspend (T) -> Unit,
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(state) {
            try {
                collect { block(it) }
            } finally {
                if (!isActive) {
                    cancelBlock?.invoke()
                }
            }
        }
    }
}

fun <T> Flow<T>.collectLatestOnCreated(
    viewLifecycleOwner: LifecycleOwner,
    cancelBlock: (() -> Unit)? = null,
    block: suspend (T) -> Unit,
) {
    collectLatestLifecycle(viewLifecycleOwner, Lifecycle.State.CREATED, cancelBlock, block)
}

fun <T> Flow<T>.collectLatestOnStarted(
    viewLifecycleOwner: LifecycleOwner,
    cancelBlock: (() -> Unit)? = null,
    block: suspend (T) -> Unit
) {
    collectLifecycle(viewLifecycleOwner, Lifecycle.State.STARTED, cancelBlock, block)
}

private fun <T> Flow<T>.collectLatestLifecycle(
    viewLifecycleOwner: LifecycleOwner,
    state: Lifecycle.State,
    cancelBlock: (() -> Unit)? = null,
    block: suspend (T) -> Unit,
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(state) {
            try {
                collectLatest { block(it) }
            } finally {
                if (!isActive) {
                    cancelBlock?.invoke()
                }
            }
        }
    }
}