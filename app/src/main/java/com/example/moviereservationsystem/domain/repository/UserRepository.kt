package com.example.moviereservationsystem.domain.repository

import com.example.moviereservationsystem.domain.model.User

interface UserRepository {
    suspend fun saveUser(user: User) : Result<Unit>
}