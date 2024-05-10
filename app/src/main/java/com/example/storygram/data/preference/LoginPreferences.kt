package com.example.storygram.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login_preferences")
class LoginPreferences private constructor(private val dataStore: DataStore<Preferences>){
    private val LOGIN_TOKEN = stringPreferencesKey("token")
    private val LOGIN_STATUS = booleanPreferencesKey("status")

    suspend fun saveToken(token : String){
        dataStore.edit {preferences ->
            preferences[LOGIN_TOKEN] = token
        }
    }

    suspend fun loginPref(){
        dataStore.edit { preferences->
            preferences[LOGIN_STATUS] = true
        }
    }

    fun getLoginStatus(): Flow<Boolean?>{
        return dataStore.data.map { preferences ->
            preferences[LOGIN_STATUS]
        }
    }

    fun getToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[LOGIN_TOKEN]
        }
    }

    suspend fun logout(){
        dataStore.edit { preferences->
            preferences[LOGIN_STATUS] = false
            preferences[LOGIN_TOKEN] = ""
        }
    }
    companion object {
        @Volatile
        private var INSTANCE: LoginPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): LoginPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = LoginPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}