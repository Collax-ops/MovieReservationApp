package com.example.moviereservationsystem.ui.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviereservationsystem.domain.usecase.GetMoviesNowPlayingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesNowPlayingUseCase: GetMoviesNowPlayingUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun getMoviesNowPlaying() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getMoviesNowPlayingUseCase().collect { result ->
                result.onSuccess {
                   movies -> _uiState.update {
                       it.copy(
                           isLoading = false,
                           movieList = movies.map { movie ->
                               MovieUiModel(
                                   title = movie.title,
                                   posterPath = movie.posterPath
                               )
                           }
                       )
                   }
                }.onFailure {
                    _uiState.update { HomeUiState(isLoading = false) }
                }
            }
        }
    }
}
