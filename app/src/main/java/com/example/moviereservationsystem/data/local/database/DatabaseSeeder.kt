package com.example.moviereservationsystem.data.local.database

import android.annotation.SuppressLint
import com.example.moviereservationsystem.data.local.model.MovieEntity
import com.example.moviereservationsystem.data.local.model.MovieScheduleEntity
import com.example.moviereservationsystem.data.local.model.SeatEntity
import com.example.moviereservationsystem.data.local.model.TheaterEntity
import com.example.moviereservationsystem.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseSeeder @Inject constructor(
    private val db: AppDatabase,
    private val movieRepositoryImpl: MovieRepositoryImpl
) {


    suspend fun prepopulateDatabase() {
        withContext(Dispatchers.IO) {
            val movieDao = db.movieDao()
            val theaterDao = db.theaterDao()
            val movieScheduleDao = db.movieScheduleDao()
            val seatDao = db.seatDao()

            val theaters = createTheaters()
            theaterDao.insertTheaters(theaters)

            movieRepositoryImpl.getMoviesNowPlaying().collect { result ->
                result.onSuccess { movies ->
                    val movieEntities = movies.map { movie ->
                        MovieEntity(
                            id = movie.id,
                            title = movie.title
                        )
                    }
                    movieDao.insertMovies(movieEntities)

                    val movieSchedules = createMovieSchedules(theaters, movieEntities)
                    movieScheduleDao.insertMovieSchedules(movieSchedules)

                    val seatEntites = createSeats(theaters)
                    seatDao.insertSeats(seatEntites)
                }
            }
        }
    }

    private fun createTheaters(): List<TheaterEntity> {
        return listOf(
            TheaterEntity(id = 1, theaterName = "Cineplanet", theaterLocation = "San Isidro, Lima", totalSeats = 96),
            TheaterEntity(id = 2, theaterName = "Cinemark", theaterLocation = "San Miguel, Lima", totalSeats = 96),
            TheaterEntity(id = 3, theaterName = "Cinepolis", theaterLocation = "Lince, Lima", totalSeats = 96)
        )
    }

    private fun createMovieSchedules(theaters: List<TheaterEntity>, movieEntities: List<MovieEntity>): List<MovieScheduleEntity> {
        val movieSchedules = mutableListOf<MovieScheduleEntity>()

        theaters.forEach { theater ->
            movieEntities.forEach { movieEntity ->
                val showTimes = listOf("12:00", "15:00", "18:00", "21:00")
                showTimes.forEach { startTime ->
                    val endTime = calculateEndTime(startTime)
                    movieSchedules.add(
                        MovieScheduleEntity(
                            theaterId = theater.id,
                            movieId = movieEntity.id,
                            startTime = startTime,
                            endTime = endTime
                        )
                    )
                }
            }
        }

        return movieSchedules
    }

    @SuppressLint("DefaultLocale")
    private fun calculateEndTime(startTime: String): String {
        val hour = startTime.substring(0, 2).toInt()
        val minute = startTime.substring(3).toInt()
        val endHour = (hour + 2) % 24
        return String.format("%02d:%02d", endHour, minute)
    }

    fun createSeats(theaters: List<TheaterEntity>): List<SeatEntity> {
        val seats = mutableListOf<SeatEntity>()
        val rows = 'A'..'H'
        val seatsPerRow = 12

        println("Creando asientos para ${theaters.size} teatros") // <- Agregado

        theaters.forEach { theater ->
            println("Generando asientos para teatro ID: ${theater.id}") // <- Agregado
            rows.forEach { row ->
                for (num in 1..seatsPerRow) {
                    seats.add(
                        SeatEntity.from(
                            row = row,
                            seatNumber = num,
                            theaterId = theater.id,
                            isAvailable = true
                        )
                    )
                }
            }
        }
        return seats
    }


}