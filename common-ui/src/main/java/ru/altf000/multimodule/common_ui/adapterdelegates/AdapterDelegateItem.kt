package ru.altf000.multimodule.common_ui.adapterdelegates

typealias DItem = AdapterDelegateItem

abstract class AdapterDelegateItem {
    abstract val identifier: Any
    abstract val data: Any?
}