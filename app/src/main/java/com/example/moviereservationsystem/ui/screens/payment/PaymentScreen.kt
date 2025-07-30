package com.example.moviereservationsystem.ui.screens.payment

import android.content.Context
import android.content.Intent
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import com.example.moviereservationsystem.ui.PaymentActivity

private const val TAG = "PaymentScreen"

@OptIn(UnstableApi::class)
@Composable
fun PaymentScreen(
    viewModel: PaymentViewModel = hiltViewModel(),
    navController: NavController,          // lo necesitarás luego para Google Pay
    context: Context = LocalContext.current
) {
    val uiState = viewModel.uiState.collectAsState().value

    Column {
        Text("Choose your Payment Method")

        Row {
            RadioButton(
                selected = uiState.selectedMethod == PaymentMethod.PAYPAL,
                onClick = { viewModel.onMethodSelected(PaymentMethod.PAYPAL) }
            )
            Text("PayPal")
        }

        Row {
            RadioButton(
                selected = uiState.selectedMethod == PaymentMethod.GOOGLE_WALLET,
                onClick = { viewModel.onMethodSelected(PaymentMethod.GOOGLE_WALLET) }
            )
            Text("Google Wallet")
        }

        Button(
            onClick = { viewModel.onPayClicked(amount = 100.00) },
            enabled = uiState.selectedMethod != PaymentMethod.NONE
        ) {
            Text("Pay")
        }
    }

    uiState.orderId?.let { orderId ->
        val intent = Intent(context, PaymentActivity::class.java).apply {
            putExtra("order_id", orderId)
        }

        context.startActivity(intent)

        viewModel.clearOrderId()
    }

    uiState.errorMessage?.let { error ->
        Log.e(TAG, "PaymentScreen error: $error")
        // Aquí podrías mostrar un Snackbar
    }
}