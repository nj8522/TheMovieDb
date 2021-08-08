package com.movie.movierecommendation.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movie.movierecommendation.viewModel.MoviePageViewModel
import java.lang.IllegalArgumentException
import kotlin.coroutines.CoroutineContext

class MoviePageFactory(private val pApplication: Application, private val pCoroutineContext : CoroutineContext)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviePageViewModel ::class.java)) {
            return MoviePageViewModel(pApplication, pCoroutineContext) as T
        }
        throw IllegalArgumentException("MoviePageFactory ViewModel Not found")
    }

}