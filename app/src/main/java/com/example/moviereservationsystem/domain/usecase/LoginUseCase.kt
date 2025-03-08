package com.example.moviereservationsystem.domain.usecase

import com.example.moviereservationsystem.domain.repository.AuthRepository
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<AuthResult> {
        return authRepository.logInUser(email, password)
    }
}