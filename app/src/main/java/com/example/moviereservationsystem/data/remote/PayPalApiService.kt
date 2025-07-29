package com.example.moviereservationsystem.data.remote

import com.example.moviereservationsystem.data.remote.model.paypal.request.AccessTokenResponseDto
import com.example.moviereservationsystem.data.remote.model.paypal.response.CaptureOrderResponseDto
import com.example.moviereservationsystem.data.remote.model.paypal.request.CreateOrderRequestDto
import com.example.moviereservationsystem.data.remote.model.paypal.response.CreateOrderResponseDto
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PayPalApiService {
    @FormUrlEncoded
    @POST("v1/oauth2/token")
    suspend fun getAccessToken (
        @Header("Authorization") basic: String,
        @Field("grant_type") grantType: String = "client_credentials"
    ): AccessTokenResponseDto

    @POST("v2/checkout/orders")
    suspend fun createOrder(
        @Header("Authorization") bearer: String,
        @Body orderRequest: CreateOrderRequestDto
    ): CreateOrderResponseDto

    @POST("v2/checkout/orders/{id}/capture")
    suspend fun captureOrder(
        @Header("Authorization") bearer: String,
        @Path("id") orderId: String
    ): CaptureOrderResponseDto
}