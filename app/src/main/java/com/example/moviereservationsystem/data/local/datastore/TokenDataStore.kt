package com.example.moviereservationsystem.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TokenDataStore @Inject constructor(@ApplicationContext private val context: Context) {
    val Context.payPalTokenDataStore: DataStore<Preferences> by preferencesDataStore(name = "payPalToken")
    private val dataStore = context.payPalTokenDataStore

    companion object {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val EXPIRES_AT = longPreferencesKey("expires_at")
    }

    suspend fun saveToken(token: String, expiresIn: Long) {
        val expiresAt = System.currentTimeMillis() + expiresIn * 1000
        dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN] = token
            prefs[EXPIRES_AT] = expiresAt
        }
    }

    suspend fun getValidToken(): String? {
        val prefs = dataStore.data.first()
        val token = prefs[ACCESS_TOKEN]
        val expiresAt = prefs[EXPIRES_AT] ?: 0L

        return if (System.currentTimeMillis() < expiresAt) token else null
    }

    suspend fun clearToken() {
        dataStore.edit { it.clear() }
    }
}