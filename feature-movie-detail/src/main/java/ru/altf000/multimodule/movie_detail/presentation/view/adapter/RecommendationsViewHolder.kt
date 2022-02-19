package ru.altf000.multimodule.movie_detail.presentation.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.altf000.multimodule.movie_detail.R

class RecommendationsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById(R.id.title)
    val poster: ImageView = view.findViewById(R.id.poster)
}