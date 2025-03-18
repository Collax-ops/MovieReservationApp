package com.example.moviereservationsystem.ui.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviereservationsystem.domain.usecase.GetGenresOfMovieListUseCase
import com.example.moviereservationsystem.domain.usecase.GetMoviesNowPlayingUseCase
import com.example.moviereservationsystem.ui.home.model.GenreUiModel
import com.example.moviereservationsystem.ui.home.model.MovieUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesNowPlayingUseCase: GetMoviesNowPlayingUseCase,
    private val getGenresOfMovieListUseCase: GetGenresOfMovieListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadMoviesAndGenres()
    }

    private fun loadMoviesAndGenres() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            try {
                val moviesDeferred = async { getMoviesNowPlayingUseCase().first() }
                val genresDeferred = async { getGenresOfMovieListUseCase().first() }

                val moviesResult = moviesDeferred.await()
                val genresResult = genresDeferred.await()

                val movieList = moviesResult.getOrDefault(emptyList()).map { movie ->
                    MovieUiModel(movie.title, movie.posterPath, movie.genreIds)
                }

                val genreList = genresResult.getOrDefault(emptyList()).map { genre ->
                    GenreUiModel(genre.id, genre.name)
                }

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        movies = movieList,
                        genres = genreList,
                        filteredMovies = movieList
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    fun filterMoviesByGenre(genreId: Int) {
        _uiState.update { currentState ->
            val filteredMovies = if (genreId == 0) {
                currentState.movies
            } else {
                currentState.movies.filter { movie -> movie.genreIds.contains(genreId) }
            }
            currentState.copy(selectedGenre = genreId, filteredMovies = filteredMovies)
        }
    }

    fun updateSelectedGenre(genreId: Int) {
        _uiState.update { it.copy(selectedGenre = genreId) }
    }
}

