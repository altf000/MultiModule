package ru.altf000.multimodule.collection_list_impl.presentation.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.altf000.multimodule.collection_list.R

class CollectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById(R.id.title)
    val description: TextView = view.findViewById(R.id.desctiption)
    val poster: ImageView = view.findViewById(R.id.poster)
}