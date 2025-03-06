package com.example.moviereservationsystem.data.repository

import com.example.moviereservationsystem.data.remote.FirebaseAuthService
import com.example.moviereservationsystem.domain.repository.AuthRepository
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuthService: FirebaseAuthService
) : AuthRepository
{
     override suspend fun logInWithEmailAndPassword(
        email: String,
        password: String
    ): Result<AuthResult> {
        return firebaseAuthService.logInWithEmailAndPassword(email, password)
    }
}