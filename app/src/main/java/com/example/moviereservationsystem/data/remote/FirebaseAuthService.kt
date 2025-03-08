package com.example.moviereservationsystem.data.remote

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseAuthService @Inject constructor(private val auth: FirebaseAuth){

    suspend fun logInUser(email: String, password: String) : Result<AuthResult> {
        return withContext(Dispatchers.IO) {
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                Result.success(result)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun signUpUser(email: String, password: String) : Result<AuthResult> {
        return withContext(Dispatchers.IO) {
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                Result.success(result)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }


}