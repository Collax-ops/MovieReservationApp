package com.example.moviereservationsystem.ui.screens.movieSchedule

import androidx.lifecycle.ViewModel
import com.example.moviereservationsystem.domain.usecase.GetAvailableMovieDatesUseCase
import com.example.moviereservationsystem.ui.utils.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieScheduleViewModel @Inject constructor(
    private val getAvailableMovieDatesUseCase: GetAvailableMovieDatesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(MovieScheduleUiState())
    val uiState: StateFlow<MovieScheduleUiState> = _uiState.asStateFlow()

    init {
        getAvailableMovieDates()
    }


    private fun getAvailableMovieDates() {
        val dates = getAvailableMovieDatesUseCase()
        _uiState.value = _uiState.value.copy(
            dates = dates.map { it.toUiModel() }
        )
    }

    fun updateSelectedDate(selectedIndex: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                dates = currentState.dates.mapIndexed { index, date ->
                    date.copy(isSelected = index == selectedIndex)
                }
            )
        }
    }

}