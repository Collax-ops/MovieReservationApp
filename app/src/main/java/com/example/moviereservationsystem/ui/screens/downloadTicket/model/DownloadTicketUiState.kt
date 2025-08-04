package com.example.moviereservationsystem.ui.screens.downloadTicket.model

import com.example.moviereservationsystem.ui.screens.downloadTicket.model.TicketUiModel


data class DownloadTicketUiState(
    val isLoading: Boolean = true,
    val ticket: TicketUiModel? = null,
    val error: String? = null
)