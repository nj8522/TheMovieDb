package com.movie.movierecommendation.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.movie.movierecommendation.repository.HomePageRepository
import com.movie.movierecommendation.retrofit.pojo.GenreList
import com.movie.movierecommendation.retrofit.pojo.Results
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MoviePageViewModel(pApplication: Application, private val pCoroutineContext : CoroutineContext)
    : AndroidViewModel(pApplication) {

    private val TAG                                                  = "MoviePageViewModel"
    private val mHomePageRepository                                  = HomePageRepository(pApplication)
    val mGenreObserver : MutableLiveData<LiveData<List<GenreList>>>  by lazy { MutableLiveData() }
    val mMovieObserver : MutableLiveData<List<Results>>              by lazy { MutableLiveData() }
    val mSelectedGenreObserver : MutableLiveData<List<GenreList>>    by lazy { MutableLiveData() }
    private var mGenreId : Int                                       = 0


    fun checkGenreTable() {
        viewModelScope.launch(pCoroutineContext) {
            val lGenres = mHomePageRepository.checkIfGenreIsEmpty()
            if (lGenres.isEmpty()) fetchAllGenre() else getGenre()
        }
    }

    fun fetchAllGenre() {
        try {
            viewModelScope.launch(pCoroutineContext) {
                val lData = mHomePageRepository.getAllGenre()
                if (lData != null && lData.isSuccessful) {
                    val lGenre = lData.body()!!
                    val lGenreList = lGenre.genreList
                    if (lGenreList.isNotEmpty()) {
                        insertGenreList(lGenreList)
                    }
                }
            }
        } catch (e: Exception) {
            Log.i(TAG, "getAllGenre: ${e.message}")
        }
    }

    fun insertGenreList(pGenreList: List<GenreList>) {
        viewModelScope.launch(pCoroutineContext) {
            val lGenreList : MutableList<GenreList> = mutableListOf()
            lGenreList.add(0, GenreList(0, "Explore", true))
            lGenreList.addAll(pGenreList)
            mHomePageRepository.insertAllGenreIntoDb(lGenreList)
            getGenre()
        }
    }

    fun getGenre() {
        viewModelScope.launch(pCoroutineContext) {
            mGenreObserver.value = mHomePageRepository.getAllGenreFromDb()
        }
    }

    fun getExploreMovies(pCurrentPage : Int, pGenreId : Int) {
        try {
            viewModelScope.launch(pCoroutineContext) {
                val lResponse = mHomePageRepository.exploreMovie(pCurrentPage, pGenreId)
                if (lResponse != null && lResponse.isSuccessful) {
                    val lBody = lResponse.body()!!
                    mMovieObserver.value = lBody.result
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun changeGenre(pPosition: Int, pGenreList: MutableList<GenreList>) {
        try {
            val lGenreList : MutableList<GenreList> = mutableListOf()
            for (pGenre in pGenreList.indices) {
                if (pGenre == pPosition) {
                    mGenreId = pGenreList[pGenre].id
                    pGenreList[pGenre].isSelectedGenre = true
                } else {
                    pGenreList[pGenre].isSelectedGenre = false
                }
                lGenreList.add(pGenreList[pGenre])
            }
            mSelectedGenreObserver.value = lGenreList
            getExploreMovies(1, mGenreId)

        } catch (e : Exception) {
            e.printStackTrace()
        }
    }



}