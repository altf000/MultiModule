package ru.altf000.multimodule.common_ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import ru.altf000.multimodule.common_entities.domain.Content

class ContentDiff(
    private val oldList: List<Content>,
    private val newList: List<Content>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val oldItem = oldList[oldPosition]
        val newItem = newList[newPosition]
        return oldItem == newItem
    }
}