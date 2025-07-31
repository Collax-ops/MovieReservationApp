package com.example.moviereservationsystem.ui.screens.downloadTicket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadTicketScreen(
    ticket: TicketUiModel = TicketUiModel(
        movieTitle = "Title Movie",
        userName = "John Doe",
        date = "2025-08-01",
        showTime = "19:30",
        seats = listOf("A1", "A2"),
        ticketPrice = 12.50,
        totalPrice = 25.00
    ),
    onDownloadClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Ticket") },
                navigationIcon = {
                    IconButton(onClick = { /* back */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // QR placeholder
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.LightGray, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("QR CODE", color = Color.DarkGray)
            }

            // Details
            Column(Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
                Text("Movie: ${ticket.movieTitle}", style = MaterialTheme.typography.bodyLarge)
                Text("Name: ${ticket.userName}", style = MaterialTheme.typography.bodyLarge)
                Text("Date: ${ticket.date}", style = MaterialTheme.typography.bodyLarge)
                Text("Time: ${ticket.showTime}", style = MaterialTheme.typography.bodyLarge)
                Text("Seats: ${ticket.seats.joinToString()}", style = MaterialTheme.typography.bodyLarge)
                Text("Ticket Price: \$${ticket.ticketPrice}", style = MaterialTheme.typography.bodyLarge)
                Text("Total: \$${ticket.totalPrice}", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
            }

            Button(
                onClick = onDownloadClick,
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

@Preview(showBackground = true)
@Composable
fun PreviewDownloadTicket() = DownloadTicketScreen()
