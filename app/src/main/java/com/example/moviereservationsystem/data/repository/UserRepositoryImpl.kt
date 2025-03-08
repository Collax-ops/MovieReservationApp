package com.example.moviereservationsystem.data.repository

import com.example.moviereservationsystem.data.local.UserDao
import com.example.moviereservationsystem.domain.model.User
import com.example.moviereservationsystem.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository{
    override suspend fun saveUser(user: User) {
        return userDao.insertUser(user)
    }
}