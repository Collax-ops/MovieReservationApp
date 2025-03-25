package com.example.moviereservationsystem.ui.navigation

sealed class AppDestination(val route: String) {

    data object Home : AppDestination("home")

    data object MovieSchedule : AppDestination( "movieSchedule/{movieId}/{posterPath}"){
        fun createRoute(movieId: Int, posterPath: String) = "movieSchedule/$movieId/$posterPath"
    }
}