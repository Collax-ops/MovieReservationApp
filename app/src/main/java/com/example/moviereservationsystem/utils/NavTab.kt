package com.example.moviereservationsystem.utils

import androidx.annotation.DrawableRes

data class NavTab(
    val label: String,
    @DrawableRes val iconRes: Int,
    val route: String
)
