package ru.altf000.multimodule.common_ui.utils

import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

fun RecyclerView.addLoadMoreListener(loadMore: (Unit) -> Unit) {
    val layoutManager = layoutManager as LinearLayoutManager
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val totalItemCount = layoutManager.itemCount
            val visibleItemCount = layoutManager.childCount
            val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
            if (visibleItemCount + lastVisibleItemPosition + 1 >= totalItemCount) {
                loadMore.invoke(Unit)
            }
        }
    })
}

fun ImageView.load(url: String) {
    Glide.with(context!!)
        .load(url)
        .centerCrop()
        .into(this)
}