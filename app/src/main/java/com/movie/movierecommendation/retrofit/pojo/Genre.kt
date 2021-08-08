package com.movie.movierecommendation.retrofit.pojo

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("genres")
    val genreList : List<GenreList>
)

data class GenreList(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String
)
