package com.example.nutriscan.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.nutriscan.model.LoginResult
import com.example.nutriscan.model.UserLogin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    private val TOKEN_KEY = stringPreferencesKey("user_token")
    private val UID_USER = stringPreferencesKey("user_uid")
    private val NAME_USER = stringPreferencesKey("user_name")
    private val EMAIL_USER = stringPreferencesKey("user_password")

    fun getToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY] ?:"No User Token"
        }
    }

    fun getUID(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[UID_USER]?:"No User Uid"
        }
    }

    fun getName(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[NAME_USER]?:"No User Name"
        }
    }

    fun getEmail(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[EMAIL_USER]?:"No User Email"
        }
    }

    suspend fun saveLoginSession(userToken: String,userUid: String, userName:String, userEmail: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = userToken
            preferences[UID_USER] = userUid
            preferences[NAME_USER] = userName
            preferences[EMAIL_USER] = userEmail
        }
    }

    suspend fun clearLoginSession() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}