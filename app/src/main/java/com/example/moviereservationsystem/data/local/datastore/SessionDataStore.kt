package com.example.moviereservationsystem.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private const val SESSION_DATASTORE_NAME = "session_prefs"

val Context.sessionDataStore: DataStore<Preferences> by preferencesDataStore(name = SESSION_DATASTORE_NAME)

@Singleton
class SessionDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.sessionDataStore

    private object Keys {
        val USER_ID = stringPreferencesKey("user_Id")
    }

    suspend fun saveUserId(userId: String) {
        dataStore.edit { prefs ->
            prefs[Keys.USER_ID] = userId
        }
    }

    val userIdFlow: Flow<String?> =
        dataStore.data.map { prefs ->
            prefs[Keys.USER_ID]
        }

    suspend fun getUserId(): String? {
        return userIdFlow.first()
    }

    suspend fun clearSession() {
        dataStore.edit { it.clear() }
    }
}