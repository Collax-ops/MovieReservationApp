package com.example.moviereservationsystem.data.mapper

import com.example.moviereservationsystem.data.local.dao.UserDao
import com.example.moviereservationsystem.data.local.model.UserEntity
import com.example.moviereservationsystem.domain.model.User
import javax.inject.Inject


fun UserEntity.mapToDomain() = User (
        id = id,
        name = name,
        email = email,
)

fun User.mapToEntity() = UserEntity (
        id = id,
        name = name,
        email = email,
)
