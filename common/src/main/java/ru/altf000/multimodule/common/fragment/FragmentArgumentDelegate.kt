package ru.altf000.multimodule.common.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class FragmentArgumentDelegate<T : Any> : ReadWriteProperty<Fragment, T> {

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val key = property.name
        @Suppress("UNCHECKED_CAST")
        return thisRef.arguments?.get(key) as? T ?: error("Property ${property.name} could not be read")
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        val args = thisRef.arguments ?: Bundle().also(thisRef::setArguments)
        val key = property.name
        args.put(key, value)
    }
}

class FragmentNullableArgumentDelegate<T : Any?> : ReadWriteProperty<Fragment, T?> {

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T? {
        val key = property.name
        @Suppress("UNCHECKED_CAST")
        return thisRef.arguments?.get(key) as? T
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T?) {
        val args = thisRef.arguments ?: Bundle().also(thisRef::setArguments)
        val key = property.name
        value?.let { args.put(key, it) } ?: args.remove(key)
    }
}

fun <T : Any> argument(): ReadWriteProperty<Fragment, T> = FragmentArgumentDelegate()

fun <T : Any> argumentNullable(): ReadWriteProperty<Fragment, T?> = FragmentNullableArgumentDelegate()