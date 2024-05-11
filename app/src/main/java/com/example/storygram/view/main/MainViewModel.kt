package com.example.storygram.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storygram.data.Result
import com.example.storygram.data.remote.response.StoryResponse
import com.example.storygram.data.repository.StoryRepository
import kotlinx.coroutines.launch

class MainViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    fun getAllStory() : LiveData<Result<StoryResponse>> {
        return storyRepository.getAllStory()
    }
}