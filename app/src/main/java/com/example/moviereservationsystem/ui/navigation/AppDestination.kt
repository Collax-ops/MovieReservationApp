package com.example.moviereservationsystem.ui.navigation

sealed class AppDestination(val route: String) {

    data object Login: AppDestination("login")

    data object SignUp: AppDestination("signup")

    data object Home : AppDestination("home")

    data object MovieSchedule : AppDestination( "movieSchedule/{movieId}/{posterPath}"){
        fun createRoute(movieId: Int, posterPath: String) = "movieSchedule/$movieId/$posterPath"
    }

    data object Seat : AppDestination("seat/{theatherId}/{scheduleId}") {
        fun createRoute(theatherId: Int,scheduleId: Int) = "seat/$theatherId/$scheduleId"
    }

    data object Payment : AppDestination("payment/{scheduleId}/{theaterId}/{seats}") {
        fun createRoute(scheduleId: Int, theaterId: Int, seats: String) =
            "payment/$scheduleId/$theaterId/$seats"
    }

    data object DownloadTicket : AppDestination("downloadTicket/{ticketId}") {
        fun createRoute(ticketId: Int) =
            "downloadTicket/$ticketId"
    }

    data object BookingHistory : AppDestination("bookingHistory")

    data object PaymentHistory : AppDestination("paymentHistory")
}