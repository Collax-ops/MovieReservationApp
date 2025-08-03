package com.example.moviereservationsystem.data.repository

import com.example.moviereservationsystem.data.remote.FirebaseAuthService
import com.example.moviereservationsystem.domain.repository.AuthRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuthService: FirebaseAuthService
) : AuthRepository
{
     override suspend fun logInUser(
        email: String,
        password: String
    ): Result<AuthResult> {
        return firebaseAuthService.logInUser(email, password)
    }

    override suspend fun signUpUser(
        email: String,
        password: String
    ): Result<AuthResult> {
        return firebaseAuthService.signUpUser(email, password)
    }

    override fun observeAuthState(): Flow<FirebaseUser?> =
        firebaseAuthService.observeAuth()

    override suspend fun signOut() =
        firebaseAuthService.signOutUser()
}