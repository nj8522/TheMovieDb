package com.movie.movierecommendation.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.movie.movierecommendation.retrofit.pojo.GenreList


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllGenre(pGenreList : MutableList<GenreList>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres(pGenreList : MutableList<GenreList>)

    @Query("SELECT * FROM movie_genres")
    fun getAllGenre() : List<GenreList>

    @Query("SELECT * FROM movie_genres")
    fun getGenreLiveData() : LiveData<List<GenreList>>

}