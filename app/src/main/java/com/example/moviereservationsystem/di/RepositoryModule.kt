package com.example.moviereservationsystem.di

import com.example.moviereservationsystem.data.repository.AuthRepositoryImpl
import com.example.moviereservationsystem.data.repository.MovieRepositoryImpl
import com.example.moviereservationsystem.data.repository.UserRepositoryImpl
import com.example.moviereservationsystem.domain.repository.AuthRepository
import com.example.moviereservationsystem.domain.repository.MovieRepository
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
}