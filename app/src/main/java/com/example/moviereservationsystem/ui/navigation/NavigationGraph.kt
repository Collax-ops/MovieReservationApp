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
import com.example.moviereservationsystem.ui.screens.movieSchedule.MovieScheduleScreen
import com.example.moviereservationsystem.ui.screens.movieSchedule.MovieScheduleViewModel
import com.example.moviereservationsystem.ui.screens.home.HomeScreen
import com.example.moviereservationsystem.ui.screens.home.HomeViewModel

@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavigationGraph(navController: NavHostController) {
    SharedTransitionLayout {
        val sharedTransitionScope = this
        NavHost(
            navController = navController,
            startDestination = AppDestination.Home.route
        ) {
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

                MovieScheduleScreen(movieScheduleViewModel,movieId,posterPath, sharedTransitionScope, this@composable)
            }
        }
    }
}
