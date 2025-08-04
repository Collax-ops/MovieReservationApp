package com.example.moviereservationsystem.ui.screens.downloadTicket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviereservationsystem.domain.model.TicketDetail
import com.example.moviereservationsystem.domain.usecase.GetTicketDetailsUseCase
import com.example.moviereservationsystem.ui.screens.downloadTicket.model.DownloadTicketUiState
import com.example.moviereservationsystem.ui.screens.downloadTicket.model.TicketUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadTicketViewModel @Inject constructor(
    private val getTicketDetailsUseCase: GetTicketDetailsUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(DownloadTicketUiState())
    val uiState: StateFlow<DownloadTicketUiState> = _uiState.asStateFlow()

    fun loadTicket(ticketId: Int, userName: String) {
        viewModelScope.launch {
            _uiState.value = DownloadTicketUiState(isLoading = true)
            try {
                val detail: TicketDetail = getTicketDetailsUseCase(ticketId, userName)

                val uiModel = TicketUiModel(
                    movieTitle = detail.movieTitle,
                    userName = detail.userName,
                    date = detail.date,
                    showTime = detail.showTime,
                    seats = detail.seats,
                    totalPrice = detail.totalPrice
                )

                _uiState.value = DownloadTicketUiState(
                    isLoading = false,
                    ticket = uiModel
                )

            } catch (e: Exception) {
                _uiState.value = DownloadTicketUiState(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}