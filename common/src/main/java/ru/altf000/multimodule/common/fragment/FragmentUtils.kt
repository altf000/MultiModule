package ru.altf000.multimodule.common.fragment

import android.os.Bundle
import android.os.Parcelable

fun <T> Bundle.put(key: String, value: T) = when (value) {
    is Boolean -> putBoolean(key, value)
    is String -> putString(key, value)
    is Int -> putInt(key, value)
    is Short -> putShort(key, value)
    is Long -> putLong(key, value)
    is Byte -> putByte(key, value)
    is ByteArray -> putByteArray(key, value)
    is Char -> putChar(key, value)
    is CharArray -> putCharArray(key, value)
    is CharSequence -> putCharSequence(key, value)
    is Float -> putFloat(key, value)
    is Bundle -> putBundle(key, value)
    is Parcelable -> putParcelable(key, value)
    else -> error("Type of property $key is not supported")
}