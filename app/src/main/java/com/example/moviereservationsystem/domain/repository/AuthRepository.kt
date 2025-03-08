package com.example.moviereservationsystem.domain.repository

import com.google.firebase.auth.AuthResult

interface AuthRepository {

    suspend fun logInUser(email: String, password: String) : Result<AuthResult>

    suspend fun signUpUser(email: String, password: String) : Result<AuthResult>
}