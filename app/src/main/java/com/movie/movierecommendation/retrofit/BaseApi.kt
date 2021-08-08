package com.movie.movierecommendation.retrofit

import com.movie.movierecommendation.retrofit.service.HomePageService
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseApi {

    private val mBaseUrl = "https://api.themoviedb.org/3/"
    private val mClient : OkHttpClient = OkHttpClient.Builder().build()

   private val movieGenreRetrofit = Retrofit.Builder()
       .baseUrl(mBaseUrl)
       .addConverterFactory(GsonConverterFactory.create())
       .client(mClient)
       .build()



    fun getAllGenre() : HomePageService {
        return movieGenreRetrofit.create(HomePageService :: class.java)
    }




}