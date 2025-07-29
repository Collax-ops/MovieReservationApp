package com.example.moviereservationsystem.di

import com.example.moviereservationsystem.BuildConfig
import com.example.moviereservationsystem.data.remote.PayPalApiService
import com.example.moviereservationsystem.data.remote.RequestInterceptor
import com.example.moviereservationsystem.data.remote.TMDBApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @TMDBClient
    @Provides
    @Singleton
    fun provideTMDBRetrofit(@TMDBClient okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.TMDB_API_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
    }

    @TMDBClient
    @Provides
    @Singleton
    fun provideTMDBOkHttpClient(requestInterceptor: RequestInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .build()
    }

    @Provides
    fun provideRequestInterceptor(): RequestInterceptor {
        return RequestInterceptor()
    }

    @Provides
    @Singleton
    fun provideTMDBApiService(@TMDBClient retrofit: Retrofit): TMDBApiService {
        return retrofit.create(TMDBApiService::class.java)
    }

    @PayPalClient
    @Provides
    @Singleton
    fun providePayPalOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @PayPalClient
    @Provides
    @Singleton
    fun providePayPalRetrofit(@PayPalClient payPalOkHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.PAYPAL_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(payPalOkHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providePayPalApiService(@PayPalClient payPalRetrofit: Retrofit): PayPalApiService {
        return payPalRetrofit.create(PayPalApiService::class.java)
    }

}