package com.example.moviereservationsystem.data.repository

import com.example.moviereservationsystem.BuildConfig
import com.example.moviereservationsystem.data.local.datastore.TokenDataStore
import com.example.moviereservationsystem.data.remote.PayPalApiService
import com.example.moviereservationsystem.data.remote.model.paypal.request.AmountDto
import com.example.moviereservationsystem.data.remote.model.paypal.request.CreateOrderRequestDto
import com.example.moviereservationsystem.data.remote.model.paypal.request.ExperienceContextDto
import com.example.moviereservationsystem.data.remote.model.paypal.request.PayPalDto
import com.example.moviereservationsystem.data.remote.model.paypal.request.PaymentSourceDto
import com.example.moviereservationsystem.data.remote.model.paypal.request.PurchaseUnitRequestDto
import com.example.moviereservationsystem.domain.repository.PayPalRepository
import javax.inject.Inject

class PayPalRepositoryImpl @Inject constructor(
    private val api: PayPalApiService,
    private val tokenDataStore: TokenDataStore
) : PayPalRepository {


    override suspend fun getAccessToken(): String {
        val cachedToken = tokenDataStore.getValidToken()

        if (!cachedToken.isNullOrEmpty()) {
            return cachedToken
        }

        val credentials = "${BuildConfig.PAYPAL_CLIENT_ID}:${BuildConfig.PAYPAL_SECRET_KEY}"
        val basicAuth = "Basic " + android.util.Base64.encodeToString(
            credentials.toByteArray(),
            android.util.Base64.NO_WRAP
        )

        val response = api.getAccessToken(basicAuth)

        tokenDataStore.saveToken(response.accessToken, response.expiresIn)

        return response.accessToken
    }

    override suspend fun createOrder(amount: Double): String {
        val accessToken = this.getAccessToken()
        val bearer = "Bearer $accessToken"

        val request = CreateOrderRequestDto(
            intent = "CAPTURE",
            paymentSource = PaymentSourceDto(
                paypal = PayPalDto(
                    experienceContext = ExperienceContextDto(
                        returnUrl = "myapp://success",
                        cancelUrl = "myapp://cancel",
                        userAction = "PAY_NOW"
                    )
                )
            ),
            purchaseUnits = listOf(
                PurchaseUnitRequestDto(
                    amount = AmountDto(
                        currencyCode = "USD",
                        value = amount.toString()
                    )
                )
            )
        )

        val response = api.createOrder(bearer, request)
        val approvalLink = response.links.firstOrNull { it.rel == "payer-action" }?.href.orEmpty()
        return approvalLink
    }

    override suspend fun captureOrder(orderId: String): String {
        val accessToken = getAccessToken()
        val bearer = "Bearer $accessToken"

        val response = api.captureOrder(bearer, orderId)
        return response.status.toString()
    }
}