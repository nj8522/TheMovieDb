package com.movie.movierecommendation.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_genre")
data class GenreTable(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "genre")
    val genre : String
)
