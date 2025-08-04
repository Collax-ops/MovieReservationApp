package com.example.moviereservationsystem.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.OptIn
import com.paypal.android.corepayments.CoreConfig
import com.paypal.android.corepayments.Environment
import com.paypal.android.paypalwebpayments.PayPalWebCheckoutClient
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.moviereservationsystem.BuildConfig
import com.example.moviereservationsystem.data.local.datastore.SessionDataStore
import com.example.moviereservationsystem.domain.model.Payment
import com.example.moviereservationsystem.domain.model.Ticket
import com.example.moviereservationsystem.domain.model.TicketSeat
import com.example.moviereservationsystem.domain.usecase.CapturePayPalOrderUseCase
import com.example.moviereservationsystem.domain.usecase.InsertTicketSeatsUseCase
import com.example.moviereservationsystem.domain.usecase.InsertTicketUseCase
import com.example.moviereservationsystem.domain.usecase.SavePaymentUseCase
import com.example.moviereservationsystem.ui.navigation.AppDestination
import com.paypal.android.paypalwebpayments.PayPalWebCheckoutFundingSource
import com.paypal.android.paypalwebpayments.PayPalWebCheckoutRequest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PaymentActivity : ComponentActivity() {

    @Inject
    lateinit var capturePayPalOrderUseCase: CapturePayPalOrderUseCase

    @Inject
    lateinit var savePaymentUseCase: SavePaymentUseCase

    @Inject
    lateinit var insertTicketUseCase: InsertTicketUseCase

    @Inject
    lateinit var insertTicketSeatsUseCase: InsertTicketSeatsUseCase

    @Inject
    lateinit var sessionDataStore: SessionDataStore

    private var alreadyLaunched = false

    // Guardar datos persistentes
    private var method: String? = null
    private var scheduleId: Int = -1
    private var theaterId: Int = -1
    private var seats: ArrayList<String> = arrayListOf()

    @OptIn(UnstableApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        method = intent?.getStringExtra("method")
        scheduleId = intent?.getIntExtra("scheduleId", -1) ?: -1
        theaterId = intent?.getIntExtra("theaterId", -1) ?: -1
        seats = intent?.getStringArrayListExtra("seats") ?: arrayListOf()

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

        lifecycleScope.launch {
            try {
                val captureResult = capturePayPalOrderUseCase(orderId)

                val userId = sessionDataStore.getUserId()

                Log.d("PaymentActivity", "👤 userId = $userId")
                Log.d("PaymentActivity", "🎬 scheduleId = $scheduleId")
                Log.d("PaymentActivity", "🏢 theaterId = $theaterId")
                Log.d("PaymentActivity", "💺 seats = $seats")

                if (scheduleId == -1 || seats.isEmpty()) {
                    Log.e("PaymentActivity", "❌ scheduleId inválido o sin asientos seleccionados.")
                    finish()
                    return@launch
                }

                val ticket = Ticket(
                    ticketId = 0,
                    userId = userId,
                    scheduleId = scheduleId,
                    totalPrice = captureResult.amount,
                    purchaseDate = captureResult.transactionDate
                )

                val ticketId = insertTicketUseCase(ticket).toInt()
                Log.d("PaymentActivity", "✅ Ticket registrado con ID: $ticketId")

                val payment = Payment(
                    ticketId = ticketId,
                    amount = captureResult.amount,
                    paymentMethod = method ?: "PAYPAL",
                    paymentStatus = captureResult.status,
                    transactionDate = captureResult.transactionDate
                )
                savePaymentUseCase(payment)
                Log.d("PaymentActivity", "✅ Pago registrado")

                val ticketSeats = seats.map { seatId ->
                    TicketSeat(ticketId = ticketId, seatId = seatId)
                }
                insertTicketSeatsUseCase(ticketSeats)
                Log.d("PaymentActivity", "✅ Asientos registrados")


                Intent(this@PaymentActivity, MainActivity::class.java).also {
                    it.putExtra("navigateTo", "downloadTicket")
                    it.putExtra("ticketId", ticketId)
                    it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(it)
                }
                finish()

            } catch (e: Exception) {
                Log.e("PaymentActivity", "❌ Error capturing payment", e)
            }
        }
    }
}