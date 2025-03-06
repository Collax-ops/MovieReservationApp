package com.example.moviereservationsystem.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviereservationsystem.ui.login.LoginScreen
import com.example.moviereservationsystem.ui.login.LoginViewModel
import com.example.moviereservationsystem.ui.theme.MovieReservationSystemTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginScreen(loginViewModel = loginViewModel)
        }
    }
}

