package com.example.moviereservationsystem.domain.repository

import com.google.firebase.auth.AuthResult

interface AuthRepository {

    suspend fun logInWithEmailAndPassword(email: String, password: String) : Result<AuthResult>
}