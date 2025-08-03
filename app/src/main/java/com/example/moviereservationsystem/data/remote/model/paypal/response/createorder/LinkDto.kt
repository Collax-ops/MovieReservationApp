package com.example.moviereservationsystem.data.remote.model.paypal.response.createorder

import kotlinx.serialization.Serializable

@Serializable
data class LinkDto(
    val href: String,
    val rel: String,
    val method: String
)