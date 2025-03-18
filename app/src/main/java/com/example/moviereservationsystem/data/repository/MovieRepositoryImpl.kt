package com.example.moviereservationsystem.data.repository


import androidx.media3.common.util.Log
import com.example.moviereservationsystem.data.mapper.GenreMapper
import com.example.moviereservationsystem.domain.repository.MovieRepository
import com.example.moviereservationsystem.data.mapper.MovieMapper
import com.example.moviereservationsystem.data.remote.TMDBApiService
import com.example.moviereservationsystem.domain.model.Genre
import com.example.moviereservationsystem.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService : TMDBApiService,
    private val movieMapper : MovieMapper,
    private val genreMapper : GenreMapper
) : MovieRepository {
    override fun getMoviesNowPlaying(): Flow<Result<List<Movie>>> = flow {
        val response = apiService.getMoviesNowPlaying()
        if(!response.isSuccessful){
            throw retrofit2.HttpException(response)
        } else {
            val movieList = response.body()?.results?.map {
                movieMapper.mapToDomain(it)
            } ?: emptyList()
            emit(Result.success(movieList))
        }
    }.flowOn(Dispatchers.IO).catch { e ->
        emit(Result.failure(e))
    }

    override fun getGenresOfMovieList(): Flow<Result<List<Genre>>> = flow {
        val response = apiService.getGenresOfMovieList()
        if (!response.isSuccessful) {
            throw retrofit2.HttpException(response)
        } else {
            val genreList = response.body()?.genres?.map {
                genreMapper.mapToDomain(it)
            } ?: emptyList()
            emit(Result.success(genreList))
        }
    }.flowOn(Dispatchers.IO).catch { e ->
        emit(Result.failure(e))
    }
}