package com.example.moviereservationsystem.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviereservationsystem.data.local.model.entities.TheaterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TheaterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTheaters(theaters: List<TheaterEntity>)

    @Query("SELECT * FROM Theaters")
    fun getAllTheaters(): Flow<List<TheaterEntity>>
}