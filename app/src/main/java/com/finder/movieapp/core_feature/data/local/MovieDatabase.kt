package com.finder.movieapp.core_feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.finder.movieapp.core_feature.data.local.entites.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao

}