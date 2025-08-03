package com.example.moviereservationsystem.ui.screens.seat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviereservationsystem.domain.usecase.GetOccupiedSeatsUseCase
import com.example.moviereservationsystem.domain.usecase.GetSeatsUseCase
import com.example.moviereservationsystem.domain.usecase.ToggleSeatUseCase
import com.example.moviereservationsystem.ui.screens.seat.model.SeatUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class SeatViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSeats: GetSeatsUseCase,
    private val getOccupiedSeatsUseCase: GetOccupiedSeatsUseCase,
    private val toggleSeat: ToggleSeatUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(SeatBookingUiState())
    val uiState: StateFlow<SeatBookingUiState> = _uiState.asStateFlow()

    private val scheduleId: Int = savedStateHandle["scheduleId"]!!


    fun loadSeats(theaterId: Int,scheduleId: Int) {
        viewModelScope.launch {
            val allSeats = getSeats(theaterId).first()
            val occupied = getOccupiedSeatsUseCase(scheduleId).first().toSet()

            val seatUiStates = allSeats.map { seat ->
                SeatUiState(
                    seatId = seat.seatId,
                    row = seat.row,
                    number = seat.number,
                    isAvailable = seat.seatId !in occupied,
                    isSelected = false
                )
            }

            _uiState.value = _uiState.value.copy(seats = seatUiStates)
        }
    }


    fun onSeatClick(seatId: String) {
        val updated = _uiState.value.seats.map { s ->
            if (s.seatId == seatId && s.isAvailable) s.copy(isSelected = !s.isSelected)
            else s
        }
        val count = updated.count { it.isSelected }
        _uiState.value = _uiState.value.copy(
            seats = updated,
            selectedCount = count
        )
    }

    fun onProceed() {
        viewModelScope.launch {
            _uiState.value.seats
                .filter { it.isSelected }
        }
    }


}