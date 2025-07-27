package com.example.moviereservationsystem

import android.app.Application
import com.example.moviereservationsystem.data.local.database.AppDatabase
import com.example.moviereservationsystem.data.local.database.DatabaseSeeder
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MovieReservationSystem : Application() {
    @Inject
    lateinit var database: AppDatabase

    @Inject
    lateinit var databaseSeeder: DatabaseSeeder

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.Default).launch {
            databaseSeeder.prepopulateDatabase()
        }
    }
}
