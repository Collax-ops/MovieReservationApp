package com.example.moviereservationsystem.ui.navigation

import android.net.Uri
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.moviereservationsystem.ui.screens.downloadTicket.DownloadTicketScreen
import com.example.moviereservationsystem.ui.screens.movieSchedule.MovieScheduleScreen
import com.example.moviereservationsystem.ui.screens.movieSchedule.MovieScheduleViewModel
import com.example.moviereservationsystem.ui.screens.home.HomeScreen
import com.example.moviereservationsystem.ui.screens.home.HomeViewModel
import com.example.moviereservationsystem.ui.screens.login.LoginScreen
import com.example.moviereservationsystem.ui.screens.login.LoginViewModel
import com.example.moviereservationsystem.ui.screens.payment.PaymentScreen
import com.example.moviereservationsystem.ui.screens.payment.PaymentViewModel
import com.example.moviereservationsystem.ui.screens.seat.SeatScreen
import com.example.moviereservationsystem.ui.screens.seat.SeatViewModel
import com.example.moviereservationsystem.ui.screens.signup.SignUpScreen
import com.example.moviereservationsystem.ui.screens.signup.SignUpViewModel

@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavigationGraph(navController: NavHostController) {
    SharedTransitionLayout {
        val sharedTransitionScope = this
        NavHost(
            navController = navController,
            startDestination = AppDestination.Login.route
        ) {
            composable(route = AppDestination.Login.route) {
                val loginViewModel: LoginViewModel = hiltViewModel()

                LoginScreen(loginViewModel,navController)
            }

            composable (route = AppDestination.SignUp.route){
                val signUpViewModel: SignUpViewModel = hiltViewModel()

                SignUpScreen(signUpViewModel,navController)
            }

            composable(route = AppDestination.Home.route) {
                val homeViewModel: HomeViewModel = hiltViewModel()

                HomeScreen(
                    navController,
                    homeViewModel,
                    sharedTransitionScope,
                    this@composable,
                )
            }

            composable(
                route = AppDestination.MovieSchedule.route,
                arguments = listOf(
                    navArgument("movieId") { type = NavType.IntType },
                    navArgument("posterPath") { type = NavType.StringType }
                )
            ) { backStackEntry ->

                val movieScheduleViewModel: MovieScheduleViewModel = hiltViewModel()

                val movieId = backStackEntry.arguments?.getInt("movieId") ?: return@composable

                val posterPath = backStackEntry.arguments?.getString("posterPath").let { Uri.decode(it) } ?: return@composable

                MovieScheduleScreen(movieScheduleViewModel,movieId,posterPath, sharedTransitionScope, this@composable, navController)
            }

            composable (
                route = AppDestination.Seat.route,
                arguments = listOf(navArgument("scheduleId") { type = NavType.IntType
                }, navArgument("theatherId") { type = NavType.IntType })
            ) { backStackEntry ->

                val scheduleId = backStackEntry.arguments?.getInt("scheduleId") ?: return@composable
                val theatherId = backStackEntry.arguments?.getInt("theatherId") ?: return@composable
                val seatViewModel: SeatViewModel = hiltViewModel()

                SeatScreen(
                    viewModel = seatViewModel,
                    theatherId = theatherId,
                    scheduleId = scheduleId,
                    navController = navController
                )
            }

            composable(
                route = AppDestination.Payment.route,
                arguments = listOf(
                    navArgument("scheduleId") { type = NavType.IntType },
                    navArgument("theaterId") { type = NavType.IntType },
                    navArgument("seats") { type = NavType.StringType }
                )
            ) {
                val paymentViewModel: PaymentViewModel = hiltViewModel()
                val scheduleId = it.arguments?.getInt("scheduleId") ?: return@composable
                val theaterId = it.arguments?.getInt("theaterId") ?: return@composable
                val seats = it.arguments?.getString("seats")?.split(",") ?: emptyList()

                PaymentScreen(
                    viewModel = paymentViewModel,
                    navController = navController,
                    scheduleId = scheduleId,
                    theaterId = theaterId,
                    selectedSeats = seats
                )
            }

            composable(
                route = "download_ticket/{ticketId}",
                arguments = listOf(navArgument("ticketId") { type = NavType.IntType })
            ) {
                val ticketId = it.arguments?.getInt("ticketId") ?: -1
                DownloadTicketScreen()
            }

        }
    }
}
