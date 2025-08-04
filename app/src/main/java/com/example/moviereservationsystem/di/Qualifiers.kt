package com.example.moviereservationsystem.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TMDBClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PayPalClient