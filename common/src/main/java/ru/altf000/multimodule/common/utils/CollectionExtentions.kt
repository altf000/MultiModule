package ru.altf000.multimodule.common.utils

fun <T> List<T>.findFromIndex(initialIndex: Int, predicate: (T) -> Boolean): T? {
    if (initialIndex >= size) return null
    for (i in initialIndex until this.size) {
        if (predicate(this[i])) return this[i]
    }
    return null
}

fun <T> List<T>.findFromIndexReverse(initialIndex: Int, predicate: (T) -> Boolean): T? {
    if (initialIndex >= size) return null
    for (i in initialIndex downTo 0) {
        if (predicate(this[i])) return this[i]
    }
    return null
}