package com.example.moviereservationsystem.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.OptIn
import com.paypal.android.corepayments.CoreConfig
import com.paypal.android.corepayments.Environment
import com.paypal.android.corepayments.PayPalSDKError
import com.paypal.android.paypalwebpayments.PayPalWebCheckoutClient
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.moviereservationsystem.BuildConfig
import com.example.moviereservationsystem.domain.repository.PayPalRepository
import com.example.moviereservationsystem.domain.usecase.CapturePayPalOrderUseCase
import com.paypal.android.paypalwebpayments.PayPalWebCheckoutFinishStartResult
import com.paypal.android.paypalwebpayments.PayPalWebCheckoutFundingSource
import com.paypal.android.paypalwebpayments.PayPalWebCheckoutRequest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PaymentActivity : ComponentActivity() {

    @Inject
    lateinit var capturePayPalOrderUseCase: CapturePayPalOrderUseCase

    private var alreadyLaunched = false

    @OptIn(UnstableApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!alreadyLaunched) {
            alreadyLaunched = true
            val orderId = intent?.getStringExtra("order_id")
            if (orderId.isNullOrBlank()) {
                finish()
                return
            }

            val config = CoreConfig(
                clientId = BuildConfig.PAYPAL_CLIENT_ID,
                environment = Environment.SANDBOX
            )

            val client = PayPalWebCheckoutClient(this, config, "myapp")

            val request = PayPalWebCheckoutRequest(orderId, PayPalWebCheckoutFundingSource.PAYPAL)
            client.start(this, request)
        }
    }

    @OptIn(UnstableApi::class)
    override fun onNewIntent(newIntent: Intent?) {
        super.onNewIntent(newIntent)
        intent = newIntent

        val uri = intent?.data
        val orderId = uri?.getQueryParameter("token") ?: return

        Log.d("PaymentActivity", "✅ Returned orderId = $orderId")

        lifecycleScope.launch {
            try {
                val result = capturePayPalOrderUseCase(orderId)
            } catch (e: Exception) {
                Log.e("PaymentActivity", "❌ Error capturing payment", e)
            }
        }
    }
}