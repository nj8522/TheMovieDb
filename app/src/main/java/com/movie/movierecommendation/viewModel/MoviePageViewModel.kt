package com.movie.movierecommendation.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.movie.movierecommendation.repository.HomePageRepository
import com.movie.movierecommendation.retrofit.pojo.GenreList
import com.movie.movierecommendation.room.table.GenreTable
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MoviePageViewModel(pApplication: Application, val pCoroutineContext : CoroutineContext)
    : AndroidViewModel(pApplication) {

    private val TAG = "MoviePageViewModel"
    private val mHomePageRepository = HomePageRepository(pApplication)

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
            val lRoomTableGenre: MutableList<GenreTable> = mutableListOf()
            for (pGenre in pGenreList) {
                lRoomTableGenre.add(GenreTable(pGenre.id, pGenre.name))
            }
            mHomePageRepository.insertAllGenreIntoDb(lRoomTableGenre)
        }
    }


    fun getGenre() {
        Log.i(TAG, "getGenre: Logic is Working")
    }

}