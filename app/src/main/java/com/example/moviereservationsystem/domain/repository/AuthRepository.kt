package com.example.moviereservationsystem.domain.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun logInUser(email: String, password: String) : Result<AuthResult>

    suspend fun signUpUser(email: String, password: String) : Result<AuthResult>

    fun observeAuthState(): Flow<FirebaseUser?>

    suspend fun signOut()
}