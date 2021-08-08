package com.movie.movierecommendation.repository

import android.app.Application
import android.util.Log
import com.movie.movierecommendation.retrofit.BaseApi
import com.movie.movierecommendation.retrofit.pojo.Genre
import com.movie.movierecommendation.retrofit.pojo.GenreList
import com.movie.movierecommendation.retrofit.service.HomePageService
import com.movie.movierecommendation.room.MovieDb
import com.movie.movierecommendation.room.table.GenreTable
import com.movie.movierecommendation.util.ApiDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class HomePageRepository(val pApplication: Application) {

    private val TAG                      = "HomePageRepository"
    private val mMovieDB                 = MovieDb.getMovieDb(pApplication)
    private val mGenreDao                = mMovieDB.movieDao()
    private val mIoDispatcher            = Dispatchers.IO


    suspend fun checkIfGenreIsEmpty() : List<GenreTable> = withContext(mIoDispatcher) {
        return@withContext mGenreDao.getAllGenre()
    }

    suspend fun getAllGenre() : Response<Genre>? = withContext(Dispatchers.IO) {
        try{
            val lBuilder : HomePageService = BaseApi.getAllGenre()
            val lGenreResponse = lBuilder.getAllGenre(ApiDetails.ACCESS_TOKEN, ApiDetails.API_KEY)
            return@withContext lGenreResponse.execute()
        } catch (e : Exception) {
            e.printStackTrace()
        }
        return@withContext null
    }

    suspend fun insertAllGenreIntoDb(pGenre : MutableList<GenreTable>) = withContext(mIoDispatcher) {
        mGenreDao.insertAllGenre(pGenre)
    }

}