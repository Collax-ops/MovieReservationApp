package com.example.moviereservationsystem.ui.screens.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviereservationsystem.data.local.datastore.SessionDataStore
import com.example.moviereservationsystem.domain.usecase.GetBookingHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingHistoryViewModel @Inject constructor(
    private val getBookingHistoryUseCase: GetBookingHistoryUseCase,
    private val sessionDataStore: SessionDataStore
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<BookingUiModel>>(emptyList())
    val uiState: StateFlow<List<BookingUiModel>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val userId = sessionDataStore.getUserId().toString()
            getBookingHistoryUseCase(userId).collectLatest { list ->
                _uiState.value = list.map {
                    BookingUiModel(
                        movieTitle = it.movieTitle,
                        date = it.purchaseDate,
                        ticketsCount = it.seatCount,
                        totalPrice = it.totalPrice,
                        movieId = it.movieId
                    )
                }
            }
        }
    }
}