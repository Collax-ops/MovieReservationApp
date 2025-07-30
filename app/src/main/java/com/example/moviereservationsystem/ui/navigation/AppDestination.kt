package com.example.moviereservationsystem.ui.navigation

sealed class AppDestination(val route: String) {

    data object Login: AppDestination("login")

    data object SignUp: AppDestination("signup")

    data object Home : AppDestination("home")

    data object MovieSchedule : AppDestination( "movieSchedule/{movieId}/{posterPath}"){
        fun createRoute(movieId: Int, posterPath: String) = "movieSchedule/$movieId/$posterPath"
    }

    data object Seat : AppDestination("seat/{movieId}/{theaterId}") {
        fun createRoute(movieId: Int, theaterId: Int) =
            "seat/$movieId/$theaterId"
    }

    data object Payment: AppDestination("payment")
}