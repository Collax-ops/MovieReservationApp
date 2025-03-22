package com.example.moviereservationsystem.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
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

    private val homeViewModel : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            MovieReservationSystemTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    val navController = rememberNavController()
                    NavigationGraph(navController)
                }
            }
        }
    }
}

