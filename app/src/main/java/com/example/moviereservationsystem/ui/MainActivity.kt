package com.example.moviereservationsystem.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.moviereservationsystem.ui.navigation.AppDestination
import com.example.moviereservationsystem.ui.navigation.NavigationGraph
import com.example.moviereservationsystem.ui.screens.home.HomeScreen
import com.example.moviereservationsystem.ui.screens.home.HomeViewModel
import com.example.moviereservationsystem.ui.screens.login.LoginScreen
import com.example.moviereservationsystem.ui.screens.login.LoginViewModel
import com.example.moviereservationsystem.ui.screens.signup.SignUpScreen
import com.example.moviereservationsystem.ui.screens.signup.SignUpViewModel
import com.example.moviereservationsystem.ui.screens.theme.MovieReservationSystemTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieReservationSystemTheme {
                navController = rememberNavController()

                NavigationGraph(navController = navController)

                handleIntentNavigation(intent)
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        if (::navController.isInitialized) {
            handleIntentNavigation(intent)
        }
    }

    private fun handleIntentNavigation(intent: Intent?) {
        intent
            ?.getStringExtra("navigateTo")
            ?.takeIf { it == "downloadTicket" }
            ?.let {
                val ticketId = intent.getIntExtra("ticketId", -1)
                if (ticketId != -1) {
                    val route = AppDestination.DownloadTicket.createRoute(ticketId)
                    navController.navigate(route) {
                        launchSingleTop = true
                    }

                    intent.removeExtra("navigateTo")
                    intent.removeExtra("ticketId")
                }
            }
    }
}
