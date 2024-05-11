package com.example.storygram.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storygram.data.remote.response.ListStoryItem
import com.example.storygram.databinding.ItemStoryBinding

class StoryAdapter : RecyclerView.Adapter<StoryAdapter.ListViewHolder>() {
    private var resultList = listOf<ListStoryItem>()
    inner class ListViewHolder(private val binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: ListStoryItem) {
            with(binding) {
                tvUsername.text = result.name
                Glide.with(root.context)
                    .load(result.photoUrl)
                    .into(imgItemPhoto)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(resultList[position])
    }

    fun getSavedResult(newList:List<ListStoryItem>?) {
        val diffCallback = StoryDiffCallback(resultList, newList ?: emptyList())
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.resultList = newList ?: emptyList()
        diffResult.dispatchUpdatesTo(this)
    }
}