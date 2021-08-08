package com.movie.movierecommendation.retrofit.service

import com.movie.movierecommendation.retrofit.pojo.Genre
import com.movie.movierecommendation.retrofit.pojo.GenreList
import com.movie.movierecommendation.util.ApiDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface HomePageService {

   @Headers(ApiDetails.HEADERS)
   @GET("genre/movie/list")
   fun getAllGenre(
       @Header("Authorization")pAccessToken : String,
       @Query("api_key") pApiKey : String
   ) : Call<Genre>


}