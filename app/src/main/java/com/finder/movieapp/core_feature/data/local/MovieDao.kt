package com.finder.movieapp.core_feature.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.finder.movieapp.core_feature.data.local.entites.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(movieEntity: MovieEntity)

    @Query("SELECT * FROM movieentity")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("DELETE FROM movieentity where movieId=:movieId")
    suspend fun deleteMovieById(movieId: Int)

    @Query("SELECT * From movieentity where movieId=:movieId")
    suspend fun getMovieById(movieId: Int): MovieEntity?
}