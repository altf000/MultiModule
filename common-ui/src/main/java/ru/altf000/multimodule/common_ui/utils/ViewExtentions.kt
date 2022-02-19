package ru.altf000.multimodule.common_ui.utils

import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun ImageView.load(url: String) {
    Glide.with(context!!)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .into(this)
}

fun <T : ViewBinding> viewBinding(bindFunction: (View) -> T) = FragmentBinding(bindFunction)

class FragmentBinding<T : ViewBinding>(private val bindFunction: (View) -> T) :
    ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {

    private var binding: T? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T =
        binding ?: run {
            thisRef.viewLifecycleOwner.lifecycle.addObserver(this)
            bindFunction(thisRef.requireView()).also { binding = it }
        }

    override fun onDestroy(owner: LifecycleOwner) {
        binding = null
    }
}
