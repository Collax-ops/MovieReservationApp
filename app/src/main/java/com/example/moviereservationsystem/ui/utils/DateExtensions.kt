package com.example.moviereservationsystem.ui.utils

import com.example.moviereservationsystem.ui.screens.movieSchedule.model.DatesUiModel
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

fun LocalDate.toUiModel(): DatesUiModel{
    return DatesUiModel(
        month = this.month.name.take(3).lowercase().replaceFirstChar {it.uppercase()},
        date = this.dayOfMonth.toString(),
        dayOfWeek = this.dayOfWeek.name.take(3).lowercase().replaceFirstChar {it.uppercase()},
        isToday = this == Clock.System.todayIn(TimeZone.currentSystemDefault())
    )
}