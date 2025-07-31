package com.example.moviereservationsystem.ui.screens.movieSchedule

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviereservationsystem.domain.usecase.GetAvailableMovieDatesUseCase
import com.example.moviereservationsystem.domain.usecase.GetMovieSchedulesUseCase
import com.example.moviereservationsystem.domain.usecase.GetTheatersUseCase
import com.example.moviereservationsystem.ui.screens.movieSchedule.model.MovieScheduleUiModel
import com.example.moviereservationsystem.ui.screens.movieSchedule.model.TheatersUiModel
import com.example.moviereservationsystem.utils.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieScheduleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAvailableMovieDatesUseCase: GetAvailableMovieDatesUseCase,
    private val getMovieSchedulesUseCase: GetMovieSchedulesUseCase,
    private val getTheatersUseCase: GetTheatersUseCase
) : ViewModel() {

    private val movieId: Int = savedStateHandle["movieId"] ?: 0

    private val _uiState = MutableStateFlow(MovieScheduleUiState())
    val uiState: StateFlow<MovieScheduleUiState> = _uiState.asStateFlow()

    init {
        getAvailableMovieDates()
        getTheaters()
    }


    private fun getAvailableMovieDates() {
        val dates = getAvailableMovieDatesUseCase()
        _uiState.value = _uiState.value.copy(
            dates = dates.map { it.toUiModel() }
        )
    }

    private fun getTheaters() {
        viewModelScope.launch {
            getTheatersUseCase().collect { theaters ->
                _uiState.value = _uiState.value.copy(
                    theaters = theaters.map { theater ->
                        TheatersUiModel(
                            theaterId = theater.id,
                            theaterName = theater.theaterName
                        )
                    }
                )
            }
        }
    }

    private fun getMovieSchedules(theatherId: Int) {
        viewModelScope.launch {
            getMovieSchedulesUseCase(movieId,theatherId).collect { schedules ->
                _uiState.value = _uiState.value.copy(
                    movieSchedules = schedules.map { schedule ->
                        MovieScheduleUiModel(
                            starTime = schedule.startTime,
                            endTime = schedule.endTime
                        )
                    }
                )
            }
        }
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

    fun updateSelectedTheater(theaterId: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedTheaterId = theaterId
            )
        }
        getMovieSchedules(theaterId)
    }

    fun toggleExpandedState(theaterId: Int) {
        _uiState.update { currentState ->
            val newExpandedState = currentState.expandedState.toMutableMap().apply {
                val currentState = this[theaterId] ?: false
                this[theaterId] = !currentState
            }
            currentState.copy(expandedState = newExpandedState)
        }
    }

}