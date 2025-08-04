package com.example.moviereservationsystem.data.mapper

import com.example.moviereservationsystem.data.local.model.entities.UserEntity
import com.example.moviereservationsystem.domain.model.User


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
