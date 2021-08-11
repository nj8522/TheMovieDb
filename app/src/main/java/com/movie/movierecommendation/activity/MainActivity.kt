package com.movie.movierecommendation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.movie.movierecommendation.adapter.GenreAdapter
import com.movie.movierecommendation.adapter.RecentMovieAdapter
import com.movie.movierecommendation.databinding.ActivityMainBinding
import com.movie.movierecommendation.databinding.ActivityMainBinding.inflate
import com.movie.movierecommendation.factory.MoviePageFactory
import com.movie.movierecommendation.listeners.PositionListeners
import com.movie.movierecommendation.retrofit.pojo.GenreList
import com.movie.movierecommendation.retrofit.pojo.Results
import com.movie.movierecommendation.viewModel.MoviePageViewModel
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity(), PositionListeners {

    private val TAG                                    = "MainActivity"
    lateinit var mBinder                               : ActivityMainBinding
    lateinit var mMoviePageViewModel                   : MoviePageViewModel

    private var mMovieAdapter : RecentMovieAdapter?    = null
    private var mGenreAdapter  : GenreAdapter?         = null

    private val mGenreList : MutableList<GenreList>   by lazy { mutableListOf() }
    private val mMovieList : MutableList<Results>      by lazy { mutableListOf() }


    private val mCoroutineContext                      = Dispatchers.Main



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinder = inflate(layoutInflater)
        setContentView(mBinder.root)

        val lFactory = MoviePageFactory(application, mCoroutineContext)
        mMoviePageViewModel = ViewModelProvider(this, lFactory).get(MoviePageViewModel :: class.java)

        mMoviePageViewModel.mGenreObserver.observe(this, {
            it.observe(this, { genreList ->
                mGenreList.clear()
                mGenreList.addAll(genreList)
                genreAdapter()
            })
        })

        mMoviePageViewModel.mMovieObserver.observe(this, {
            mMovieList.clear()
            mMovieList.addAll(it)
            movieAdapter()
        })

        mMoviePageViewModel.mSelectedGenreObserver.observe(this, {
            mGenreList.clear()
            mGenreList.addAll(it)
            Log.i(TAG, "onCreate: $it")
            genreAdapter()
        })
    }

    override fun onStart() {
        super.onStart()
        mMoviePageViewModel.apply {
            checkGenreTable()
            getExploreMovies(1,0)
        }

    }

    private fun movieAdapter() {
        if (mMovieAdapter == null) {
            mBinder.mRecentMovieRV.apply {
                mMovieAdapter = RecentMovieAdapter(mMovieList, this@MainActivity)
                layoutManager = GridLayoutManager(context, 2)
                adapter = mMovieAdapter
                setHasFixedSize(true)
            }
        } else {
            mMovieAdapter!!.notifyDataSetChanged()
        }
    }

    private fun genreAdapter() {
        if (mGenreAdapter == null) {
            mBinder.mMovieGenre.apply {
                mGenreAdapter = GenreAdapter(mGenreList, this@MainActivity, this@MainActivity)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = mGenreAdapter
                setHasFixedSize(true)
            }
        } else {
            mGenreAdapter!!.notifyDataSetChanged()
        }
    }

    override fun getGenrePosition(adapterPosition: Int) {
        mMoviePageViewModel.changeGenre(adapterPosition, mGenreList)
    }
}