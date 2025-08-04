package com.example.moviereservationsystem.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviereservationsystem.data.local.model.entities.MovieScheduleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieScheduleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieSchedules(movieSchedule: List<MovieScheduleEntity>)

    @Query("SELECT * FROM movie_schedules WHERE movieId = :movieId AND theaterId = :theaterId")
    fun getSchedulesForMovieAndTheater(movieId: Int, theaterId: Int): Flow<List<MovieScheduleEntity>>

}