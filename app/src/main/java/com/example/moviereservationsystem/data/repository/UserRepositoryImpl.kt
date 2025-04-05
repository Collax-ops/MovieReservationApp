package com.example.moviereservationsystem.data.repository

import com.example.moviereservationsystem.data.local.dao.UserDao
import com.example.moviereservationsystem.data.mapper.mapToEntity
import com.example.moviereservationsystem.domain.model.User
import com.example.moviereservationsystem.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository{
    override suspend fun saveUser(user: User) : Result<Unit> {
        return runCatching {
            userDao.insertUser(user.mapToEntity())
        }
    }
}