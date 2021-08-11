package com.movie.movierecommendation.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.movie.movierecommendation.retrofit.pojo.GenreList
import com.movie.movierecommendation.room.table.GenreTable

@Database(entities = [GenreList :: class], version = 1, exportSchema = false)
abstract class MovieDb : RoomDatabase() {

    abstract fun movieDao() : MovieDao

    companion object {
        @Volatile
        private var INSTANCE : MovieDb? = null

        fun getMovieDb(pContext : Context) : MovieDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    pContext,
                    MovieDb :: class.java,
                    "movie_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}