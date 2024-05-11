package com.example.storygram.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.storygram.data.remote.response.ListStoryItem

class StoryDiffCallback(private val oldSavedList: List<ListStoryItem>, private val newSavedList: List<ListStoryItem>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldSavedList.size
    }

    override fun getNewListSize(): Int {
        return newSavedList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldSavedList[oldItemPosition].id == newSavedList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFav = oldSavedList[oldItemPosition]
        val newFav = newSavedList[newItemPosition]
        return oldFav.photoUrl == newFav.photoUrl && oldFav.name == newFav.name
    }
}