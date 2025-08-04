package com.example.moviereservationsystem.ui.screens.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviereservationsystem.domain.model.Genre
import com.example.moviereservationsystem.domain.model.Movie
import com.example.moviereservationsystem.domain.usecase.GetGenresOfMovieListUseCase
import com.example.moviereservationsystem.domain.usecase.GetMoviesNowPlayingUseCase
import com.example.moviereservationsystem.ui.screens.home.model.GenreUiModel
import com.example.moviereservationsystem.ui.screens.home.model.MovieUiModel
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
                val movieResult = async { fetchMovies() }
                val genreResult = async { fetchGenres() }

                val movies = movieResult.await()
                val genres = genreResult.await()

                updateUiState(movies, genres, movies.getOrDefault(emptyList()))
            } catch (e: Exception) {
                handleLoadingError()
            }
        }
    }

    private suspend fun fetchMovies() : Result<List<MovieUiModel>> {
        return getMoviesNowPlayingUseCase().first().mapCatching {
                movies -> movies.map { movie -> MovieUiModel(movie.id,movie.title,movie.posterPath, movie.genreIds) }
        }
    }

    private suspend fun fetchGenres() : Result<List<GenreUiModel>> {
        return getGenresOfMovieListUseCase().first().mapCatching {
            genres -> genres.map { genre -> GenreUiModel(genre.id, genre.name) }
        }
    }

    private fun updateUiState(
        movies: Result<List<MovieUiModel>>,
        genres: Result<List<GenreUiModel>>,
        filteredMovies: List<MovieUiModel>
    ) {
        _uiState.update {
            it.copy(
                isLoading = false,
                movies = movies.getOrDefault(emptyList()),
                genres = genres.getOrDefault(emptyList()),
                filteredMovies = filteredMovies
            )
        }
    }

    private fun handleLoadingError() {
        _uiState.update { it.copy(isLoading = false, isError = true) }
    }


    private fun filterMoviesByGenre(genreId: Int) {
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
        filterMoviesByGenre(genreId)
    }
}

