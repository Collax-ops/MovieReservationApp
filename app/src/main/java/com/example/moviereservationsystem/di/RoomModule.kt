package com.example.moviereservationsystem.di

import android.content.Context
import androidx.room.Room
import com.example.moviereservationsystem.data.local.database.AppDatabase
import com.example.moviereservationsystem.data.local.database.DatabaseSeeder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val APP_DATABASE_NAME = "MovieDB"

    @Singleton
    @Provides
    fun provideRoom(
        @ApplicationContext context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()


    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @Singleton
    @Provides
    fun provideMovieDao(db: AppDatabase) = db.movieDao()

    @Singleton
    @Provides
    fun provideTheaterDao(db: AppDatabase) = db.theaterDao()

    @Singleton
    @Provides
    fun provideMovieScheduleDao(db: AppDatabase) = db.movieScheduleDao()

    @Singleton
    @Provides
    fun provideSeatDao(db: AppDatabase) = db.seatDao()

    @Singleton
    @Provides
    fun providePaymentDao(db: AppDatabase) = db.paymentDao()

    @Singleton
    @Provides
    fun provideTicketDao(db: AppDatabase) = db.ticketDao()

    @Singleton
    @Provides
    fun provideTicketSeatDao(db: AppDatabase) = db.ticketSeatDao()

}