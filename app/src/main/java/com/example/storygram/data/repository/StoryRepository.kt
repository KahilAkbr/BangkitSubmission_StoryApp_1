package com.example.storygram.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.storygram.data.preference.LoginPreferences
import com.example.storygram.data.remote.response.RegisterResponse
import com.example.storygram.data.remote.retrofit.ApiService
import com.google.gson.Gson
import retrofit2.HttpException
import com.example.storygram.data.Result
import com.example.storygram.data.remote.response.LoginResponse

class StoryRepository (
    private var apiService: ApiService,
    private val loginPreferences: LoginPreferences,
){
    fun register(name: String, email: String, password: String) : LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.register(name, email, password)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val error = Gson().fromJson(jsonInString, RegisterResponse::class.java)
            emit(Result.Error(error.message.toString()))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun login(email : String, password: String) : LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(email, password)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val error = Gson().fromJson(jsonInString, LoginResponse::class.java)
            emit(Result.Error(error.message.toString()))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun saveToken(token : String) = loginPreferences.saveToken(token)

    suspend fun loginPref() = loginPreferences.loginPref()

    fun getLoginStatus() = loginPreferences.getLoginStatus()
    suspend fun logout() = loginPreferences.logout()
    companion object {
        @Volatile
        private var instance: StoryRepository? = null

        fun getInstance(apiService: ApiService, preferences: LoginPreferences): StoryRepository =
            instance ?: synchronized(this) {
                instance ?: StoryRepository(apiService, preferences).also { instance = it }
            }
    }
}