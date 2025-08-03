package com.example.moviereservationsystem.ui.screens.payment

import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavHostController
import com.example.moviereservationsystem.R
import com.example.moviereservationsystem.ui.PaymentActivity
import kotlin.OptIn
import androidx.compose.runtime.getValue

private const val TAG = "PaymentScreen"


@androidx.annotation.OptIn(UnstableApi::class)
@Composable
fun PaymentScreen(
    viewModel: PaymentViewModel,
    navController: NavHostController,
    theaterId: Int,
    scheduleId: Int,
    selectedSeats: List<String>
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    )
    {
        Payment(Modifier.align(Alignment.Center),viewModel, navController,theaterId,scheduleId,selectedSeats)

    }
}

@Composable
fun Payment(
    modifier: Modifier,
    viewModel: PaymentViewModel,
    navController: NavHostController,
    theaterId: Int,
    scheduleId: Int,
    selectedSeats: List<String>
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = { PaymentTopBar(onBack = { navController.popBackStack() }) }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            PaymentTitle()
            PaymentMethods(
                selected = uiState.selectedMethod,
                onSelect = { viewModel.onMethodSelected(it) }
            )
            PayButton(
                enabled = uiState.selectedMethod != PaymentMethod.NONE,
                onClick = { viewModel.onPayClicked(amount = 100.0) }
            )
        }

        PaymentNavigation(
            orderId = uiState.orderId,
            onNavigate = { method, orderId ->
                val intent = Intent(context, PaymentActivity::class.java).apply {
                    putExtra("method", method)
                    putExtra("order_id", orderId)
                    putExtra("scheduleId", scheduleId)
                    putExtra("theaterId", theaterId)
                    putStringArrayListExtra("seats", ArrayList(selectedSeats))
                }
                context.startActivity(intent)
                viewModel.clearOrderId()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentTopBar(onBack: () -> Unit) {
    TopAppBar(
        title = { Text("Payment", style = MaterialTheme.typography.titleMedium) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back_icon),
                    contentDescription = "Back"
                )
            }
        }
    )
}

@Composable
fun PaymentTitle() {
    Text(
        text = "Choose your\nPayment Method",
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PaymentMethods(
    selected: PaymentMethod,
    onSelect: (PaymentMethod) -> Unit
) {
    Column {
        PaymentMethodItem(
            label = "PayPal",
            iconRes = R.drawable.paypal_icon,
            selected = selected == PaymentMethod.PAYPAL
        ) { onSelect(PaymentMethod.PAYPAL) }
    }
}

@Composable
fun PaymentMethodItem(
    label: String,
    @DrawableRes iconRes: Int,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = onSelect)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onSelect
        )
        Spacer(Modifier.width(8.dp))
        Icon(
            painter = painterResource(iconRes),
            contentDescription = label,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(12.dp))
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun PayButton(
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = "Pay", fontSize = 16.sp)
    }
}


@Composable
fun PaymentNavigation(
    orderId: String?,
    onNavigate: (method: String, orderId: String) -> Unit
) {
    orderId?.let { id ->
        LaunchedEffect(id) {
            onNavigate("PAYPAL", id)
        }
    }
}