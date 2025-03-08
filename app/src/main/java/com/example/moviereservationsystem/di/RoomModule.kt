package com.example.moviereservationsystem.di

import android.content.Context
import androidx.room.Room
import com.example.moviereservationsystem.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val APP_DATABASE_NAME = "MovieDB"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()
}