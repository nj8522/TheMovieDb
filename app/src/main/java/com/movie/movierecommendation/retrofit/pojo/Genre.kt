package com.movie.movierecommendation.retrofit.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("genres")
    val genreList : List<GenreList>
)

@Entity(tableName = "movie_genres")
data class GenreList(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id : Int,
    @ColumnInfo(name = "genre")
    @SerializedName("name")
    val name : String,
    @ColumnInfo(name = "isSelectedGenre")
    var isSelectedGenre : Boolean = false
)
