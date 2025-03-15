package com.example.moviereservationsystem.di

import com.example.moviereservationsystem.BuildConfig
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

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .baseUrl(BuildConfig.TMDB_API_BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(requestInterceptor: RequestInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .build()
    }

    @Provides
    fun provideRequestInterceptor() : RequestInterceptor {
        return RequestInterceptor()
    }

    @Provides
    @Singleton
    fun provideTMDBApiService(retrofit: Retrofit) : TMDBApiService {
        return retrofit.create(TMDBApiService::class.java)
    }

}