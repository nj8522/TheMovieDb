package com.movie.movierecommendation.retrofit.service

import com.movie.movierecommendation.retrofit.pojo.Genre
import com.movie.movierecommendation.retrofit.pojo.GenreList
import com.movie.movierecommendation.retrofit.pojo.Movie
import com.movie.movierecommendation.util.ApiDetails
import retrofit2.Call
import retrofit2.http.*

interface HomePageService {

   @Headers(ApiDetails.HEADERS)
   @GET("genre/movie/list")
   fun getAllGenre(
       @Header("Authorization")pAccessToken : String,
       @Query("api_key") pApiKey : String
   ) : Call<Genre>


   @Headers(ApiDetails.HEADERS)
   @GET("discover/movie")
   fun exploreMovies(
       @Header("Authorization")pAccessToken : String,
       @QueryMap pExploreQuery : MutableMap<String, String>
   ) : Call<Movie>

}