package com.example.storygram.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storygram.data.repository.StoryRepository
import kotlinx.coroutines.launch

class MainViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    fun logout(){
        viewModelScope.launch {
            storyRepository.logout()
        }
    }
}