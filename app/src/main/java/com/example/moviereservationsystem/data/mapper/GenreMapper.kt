package com.example.moviereservationsystem.data.mapper

import com.example.moviereservationsystem.data.model.GenreDto
import com.example.moviereservationsystem.domain.model.Genre
import javax.inject.Inject

class GenreMapper @Inject constructor() {
    fun mapToDomain (dto: GenreDto) : Genre {
        return Genre(
            id = dto.id,
            name = dto.name
        )
    }
}