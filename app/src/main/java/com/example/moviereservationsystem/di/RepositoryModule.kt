package com.example.moviereservationsystem.di

import com.example.moviereservationsystem.data.repository.AuthRepositoryImpl
import com.example.moviereservationsystem.data.repository.MovieRepositoryImpl
import com.example.moviereservationsystem.data.repository.MovieScheduleRepositoryImpl
import com.example.moviereservationsystem.data.repository.SeatRepositoryImpl
import com.example.moviereservationsystem.data.repository.TheaterRepositoryImpl
import com.example.moviereservationsystem.data.repository.UserRepositoryImpl
import com.example.moviereservationsystem.domain.repository.AuthRepository
import com.example.moviereservationsystem.domain.repository.MovieRepository
import com.example.moviereservationsystem.domain.repository.MovieScheduleRepository
import com.example.moviereservationsystem.domain.repository.SeatRepository
import com.example.moviereservationsystem.domain.repository.TheaterRepository
import com.example.moviereservationsystem.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ) : AuthRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ) : UserRepository

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ) : MovieRepository

    @Binds
    @Singleton
    abstract fun bindTheaterRepository(
        theaterRepositoryImpl: TheaterRepositoryImpl
    ) : TheaterRepository

    @Binds
    @Singleton
    abstract fun bindMovieScheduleRepository(
        movieScheduleRepositoryImpl: MovieScheduleRepositoryImpl
    ) : MovieScheduleRepository

    @Binds
    @Singleton
    abstract fun bindSeatRepository(
        seatRepositoryImpl: SeatRepositoryImpl
    ) : SeatRepository



}