package com.example.moviereservationsystem.ui.screens.paymentHistory

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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.moviereservationsystem.ui.navigation.AppDestination
import com.example.moviereservationsystem.ui.screens.paymentHistory.model.PaymentHistoryUiModel
import com.example.moviereservationsystem.utils.NavigationBar
import com.example.moviereservationsystem.utils.TopBarWithTMDB

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentHistoryScreen(
    viewModel: PaymentHistoryViewModel,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopBarWithTMDB(title = "Payment History",onAboutClick = {navController.navigate(AppDestination.About.route)}) },
        bottomBar = { NavigationBar(navController) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(uiState.history) { item ->
                PaymentItem(item)
                Divider()
            }
        }
    }
}

@Composable
fun PaymentItem(item: PaymentHistoryUiModel) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(item.methodIconRes),
            contentDescription = item.status,
            modifier = Modifier.size(32.dp)
        )
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(item.date, style = MaterialTheme.typography.bodyLarge)
            Text("$${item.amount}", style = MaterialTheme.typography.bodyMedium)
        }
        Text(
            item.status,
            color = when (item.status) {
                "Successful" -> Color(0xFF4CAF50)
                "Failed"     -> Color.Red
                else         -> Color.DarkGray
            },
            style = MaterialTheme.typography.bodyMedium
        )
    }
}