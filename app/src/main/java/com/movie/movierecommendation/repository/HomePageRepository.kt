package com.movie.movierecommendation.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.movie.movierecommendation.retrofit.BaseApi
import com.movie.movierecommendation.retrofit.pojo.Genre
import com.movie.movierecommendation.retrofit.pojo.GenreList
import com.movie.movierecommendation.retrofit.pojo.Movie
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


    suspend fun checkIfGenreIsEmpty() : List<GenreList> = withContext(mIoDispatcher) {
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

    suspend fun insertAllGenreIntoDb(pGenre : MutableList<GenreList>) = withContext(mIoDispatcher) {
        mGenreDao.insertAllGenre(pGenre)
    }

   fun getAllGenreFromDb() : LiveData<List<GenreList>> = mGenreDao.getGenreLiveData()

    suspend fun exploreMovie(pCurrentPage : Int, pGenreId : Int) : Response<Movie>? = withContext(mIoDispatcher) {
        try {
            val lBuilder : HomePageService = BaseApi.getAllGenre()
            val lMovieQuery : MutableMap<String, String> = mutableMapOf()
            lMovieQuery["api_key"] = ApiDetails.API_KEY
            lMovieQuery["page"] = pCurrentPage.toString()
            if (pGenreId != 0) lMovieQuery["with_genres"] = pGenreId.toString()
            val lExploreMovies = lBuilder.exploreMovies(ApiDetails.ACCESS_TOKEN, lMovieQuery)
            return@withContext lExploreMovies.execute()
        } catch (e : Exception) {
            e.printStackTrace()
        }
        return@withContext null
    }


}