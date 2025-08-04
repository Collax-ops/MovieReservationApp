package com.example.moviereservationsystem.data.remote.model.paypal.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PayPalDto (
    @SerialName("experience_context")
    val experienceContext: ExperienceContextDto
)