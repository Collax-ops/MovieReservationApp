package com.example.moviereservationsystem.ui.screens.booking

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviereservationsystem.ui.navigation.AppDestination
import com.example.moviereservationsystem.utils.NavigationBar
import com.example.moviereservationsystem.utils.TopBarWithTMDB


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBookingScreen(
    viewModel: BookingHistoryViewModel,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopBarWithTMDB(title = "My Bookings",onAboutClick = {navController.navigate(AppDestination.About.route)}) },
        bottomBar = { NavigationBar(navController) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(uiState) { item ->
                BookingCard(item)
            }
        }
    }
}

@Composable
fun BookingCard(item: BookingUiModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(item.posterRes),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (item.movieTitle.isNotBlank())
                        item.movieTitle
                    else
                        "Movie ID: ${item.movieId}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(item.date, style = MaterialTheme.typography.bodySmall)
                Text(
                    "Tickets: ${item.ticketsCount}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(
                text = "$${item.totalPrice}",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}


