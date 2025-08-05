package com.example.moviereservationsystem.ui.screens.downloadTicket

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviereservationsystem.R
import com.example.moviereservationsystem.ui.navigation.AppDestination
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadTicketScreen(
    ticketId: Int,
    viewModel: DownloadTicketViewModel,
    onBack: () -> Boolean,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    val userName = FirebaseAuth.getInstance().currentUser?.displayName ?: "Guest"

    LaunchedEffect(ticketId) {
        viewModel.loadTicket(ticketId, userName)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Ticket") },
                navigationIcon = {
                    IconButton(onClick = { onBack }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(Modifier.fillMaxSize().padding(padding)) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
                uiState.error != null -> {
                    Text(
                        text = "Error: ${uiState.error}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                uiState.ticket != null -> {
                    val ticket = uiState.ticket!!
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        // QR placeholder for future integration
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )

                        // Details
                        Column(Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
                            Text("Movie: ${ticket.movieTitle}", style = MaterialTheme.typography.bodyLarge)
                            Text("Name: ${ticket.userName}", style = MaterialTheme.typography.bodyLarge)
                            Text("Date: ${ticket.date}", style = MaterialTheme.typography.bodyLarge)
                            Text("Time: ${ticket.showTime}", style = MaterialTheme.typography.bodyLarge)
                            Text("Seats: ${ticket.seats.joinToString()}", style = MaterialTheme.typography.bodyLarge)
                            Text(
                                "Total: \$${ticket.totalPrice}",
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                            )
                        }

                        Button(
                            onClick = { navController.navigate(AppDestination.Home.route) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Download Ticket")
                        }
                    }
                }
            }
        }
    }
}