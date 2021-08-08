package com.movie.movierecommendation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.movie.movierecommendation.adapter.GenreAdapter
import com.movie.movierecommendation.adapter.RecentMovieAdapter
import com.movie.movierecommendation.databinding.ActivityMainBinding
import com.movie.movierecommendation.databinding.ActivityMainBinding.inflate
import com.movie.movierecommendation.factory.MoviePageFactory
import com.movie.movierecommendation.viewModel.MoviePageViewModel
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {

    lateinit var mBinder                     : ActivityMainBinding
    lateinit var mMoviePageViewModel         : MoviePageViewModel

    lateinit var mMovieAdapter               : RecentMovieAdapter
    lateinit var mGenreAdapter               : GenreAdapter

    private val mCoroutineContext            = Dispatchers.Main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinder = inflate(layoutInflater)
        setContentView(mBinder.root)

        val lFactory = MoviePageFactory(application, mCoroutineContext)
        mMoviePageViewModel = ViewModelProvider(this, lFactory).get(MoviePageViewModel :: class.java)

        movieAdapter()
        genreAdapter()
        getGenre()

        //mMoviePageViewModel.getAllGenre()
    }

    private fun movieAdapter() {
        mBinder.mRecentMovieRV.apply {
            mMovieAdapter = RecentMovieAdapter(mutableListOf("Hello"))
            layoutManager = GridLayoutManager(context, 2)
            adapter = mMovieAdapter
            setHasFixedSize(true)
        }
        mMovieAdapter.notifyDataSetChanged()
    }

    private fun genreAdapter() {
        mBinder.mMovieGenre.apply {
            mGenreAdapter = GenreAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = mGenreAdapter
            setHasFixedSize(true)
        }
    }

    private fun getGenre() {
        mBinder.floatingActionButton.setOnClickListener {
                mMoviePageViewModel.checkGenreTable()
            }
    }

}