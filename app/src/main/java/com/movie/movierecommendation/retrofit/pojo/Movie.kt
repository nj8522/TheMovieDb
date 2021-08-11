package com.movie.movierecommendation.retrofit.pojo

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val result : List<Results>
 )

data class  Results(
    @SerializedName("adult")
    val adult : Boolean,
    @SerializedName("backdrop_path")
    val backDropPath : String,
    @SerializedName("id")
    val id : Int,
    @SerializedName("original_language")
    val originalLanguage : String,
    @SerializedName("original_title")
    val originalTitle : String,
    @SerializedName("overview")
    val movieDescription : String,
    @SerializedName("poster_path")
    val posterPath : String
)