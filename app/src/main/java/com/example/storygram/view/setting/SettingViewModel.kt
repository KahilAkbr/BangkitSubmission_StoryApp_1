package com.example.storygram.view.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storygram.data.repository.StoryRepository
import kotlinx.coroutines.launch

class SettingViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    fun logout(){
        viewModelScope.launch {
            storyRepository.logout()
        }
    }
}