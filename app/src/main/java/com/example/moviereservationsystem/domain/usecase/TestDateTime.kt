package com.example.moviereservationsystem.domain.usecase

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn

fun main(){

    val availableListOfDates = mutableListOf<LocalDate>()

    val today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())

    var x:Int = 0
    while (x < 4){
        val oneDayLater = today.plus(x, DateTimeUnit.DAY)
        availableListOfDates.add(oneDayLater)
        x++
    }

    print(availableListOfDates)

    val formattedDate = "${today.month.name.take(3).lowercase().replaceFirstChar { it.uppercase() }}. ${today.dayOfMonth} ${today.dayOfWeek.name.take(3).lowercase().replaceFirstChar { it.uppercase() }}."

}