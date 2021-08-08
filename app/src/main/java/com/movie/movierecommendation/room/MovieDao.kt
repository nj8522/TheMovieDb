package com.movie.movierecommendation.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.movie.movierecommendation.room.table.GenreTable

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllGenre(pGenreList : MutableList<GenreTable>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres(pGenreList : MutableList<GenreTable>)

    @Query("SELECT * FROM movie_genre")
    fun getAllGenre() : List<GenreTable>
}