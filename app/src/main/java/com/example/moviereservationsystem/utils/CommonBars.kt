package com.example.moviereservationsystem.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviereservationsystem.ui.navigation.AppDestination
import com.example.moviereservationsystem.ui.screens.theme.inversePrimaryDark
import com.example.moviereservationsystem.ui.screens.theme.onPrimaryContainerLight
import com.example.moviereservationsystem.ui.screens.theme.onPrimaryLight
import com.example.moviereservationsystem.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithTMDB(
    title: String = "Home",
    onAboutClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onAboutClick) {
                Icon(
                    painter = painterResource(R.drawable.info_icon),
                    contentDescription = "About"
                )
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.width(8.dp))
                Image(
                    painter = painterResource(R.drawable.tmdb_logo),
                    contentDescription = "TMDB Logo",
                    modifier = Modifier.height(24.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = onProfileClick) {
                Icon(
                    painter = painterResource(R.drawable.user_icon),
                    contentDescription = "User Icon"
                )
            }
        }
    )
}

@Composable
fun NavigationBar(navController: NavController) {
    val tabs = listOf(
        NavTab("Home", R.drawable.home_icon, AppDestination.Home.route),
        NavTab("Bookings", R.drawable.booking_icon, AppDestination.BookingHistory.route),
        NavTab("Payments", R.drawable.history_icon, AppDestination.PaymentHistory.route)
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    NavigationBar {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    navController.navigate(tab.route) {
                        // Evita apilar varias instancias
                        launchSingleTop = true
                        restoreState = true
                        // Limpia hasta la root si quieres
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(tab.iconRes),
                        contentDescription = tab.label
                    )
                },
                label = { Text(tab.label) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = inversePrimaryDark,
                    unselectedIconColor = onPrimaryContainerLight
                )
            )
        }
    }
}
