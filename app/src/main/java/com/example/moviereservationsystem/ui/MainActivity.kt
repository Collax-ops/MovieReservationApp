package com.example.moviereservationsystem.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviereservationsystem.ui.home.HomeScreen
import com.example.moviereservationsystem.ui.home.HomeViewModel
import com.example.moviereservationsystem.ui.login.LoginScreen
import com.example.moviereservationsystem.ui.login.LoginViewModel
import com.example.moviereservationsystem.ui.signup.SignUpScreen
import com.example.moviereservationsystem.ui.signup.SignUpViewModel
import com.example.moviereservationsystem.ui.theme.MovieReservationSystemTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           HomeScreen(homeViewModel)
        }
    }
}

