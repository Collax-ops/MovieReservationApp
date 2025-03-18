package com.example.moviereservationsystem.data.remote

import com.example.moviereservationsystem.data.model.GenreListResponse
import com.example.moviereservationsystem.data.model.MovieListResponse
import dagger.Provides
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface TMDBApiService {
    @GET("movie/now_playing")
    suspend fun getMoviesNowPlaying(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ) : Response<MovieListResponse>

    @GET("genre/movie/list")
    suspend fun getGenresOfMovieList(
        @Query("language") language: String = "en-US"
    ) : Response<GenreListResponse>
}