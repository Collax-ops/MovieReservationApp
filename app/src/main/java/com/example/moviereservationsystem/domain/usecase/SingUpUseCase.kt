package com.example.moviereservationsystem.domain.usecase

import android.util.Log
import com.example.moviereservationsystem.domain.model.User
import com.example.moviereservationsystem.domain.repository.AuthRepository
import com.example.moviereservationsystem.domain.repository.UserRepository
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class SingUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
){
    suspend operator fun invoke(name:String,email: String, password: String): Result<AuthResult> {
        val signUpResult = authRepository.signUpUser(email, password)

        if(signUpResult.isSuccess) {
            val firebaseUser = signUpResult.getOrNull()?.user
            if (firebaseUser != null) {
                val user = User(
                    id = firebaseUser.uid,
                    name = name,
                    email = email,
                )
                val saveResult = runCatching { userRepository.saveUser(user) }

                if (saveResult.isFailure) {
                    return Result.failure(saveResult.exceptionOrNull() ?: Exception("Error al guardar usuario"))
                }
            }
        }
        return signUpResult
    }
}