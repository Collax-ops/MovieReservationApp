package com.example.moviereservationsystem.domain.usecase

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import javax.inject.Inject


class GetAvailableMovieDatesUseCase @Inject constructor() {
    operator fun invoke (): List<LocalDate> {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val daysToShow = 4

        return (0 until daysToShow).map { today.plus(it, DateTimeUnit.DAY) }
    }
}