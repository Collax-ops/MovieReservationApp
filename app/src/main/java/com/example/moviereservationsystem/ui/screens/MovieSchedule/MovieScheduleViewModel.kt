package com.example.moviereservationsystem.ui.screens.MovieSchedule

import com.example.moviereservationsystem.domain.usecase.GetAvailableMovieDatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MovieScheduleViewModel @Inject constructor(
    private val getAvailableMovieDatesUseCase: GetAvailableMovieDatesUseCase
) {
    private val _uiState = MutableStateFlow(MovieScheduleUiState())
    val uiState: StateFlow<MovieScheduleUiState> = _uiState.asStateFlow()



}