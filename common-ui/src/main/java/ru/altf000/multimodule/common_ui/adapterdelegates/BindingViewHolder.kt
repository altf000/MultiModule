package ru.altf000.multimodule.common_ui.adapterdelegates

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

typealias BindingVH = BindingViewHolder<*>

class BindingViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)