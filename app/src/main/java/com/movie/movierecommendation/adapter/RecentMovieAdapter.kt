package com.movie.movierecommendation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.movie.movierecommendation.R
import com.movie.movierecommendation.databinding.HomeScreenLayoutBinding
import com.movie.movierecommendation.retrofit.pojo.Results

class RecentMovieAdapter(private val pRecentMovieList : MutableList<Results>, private val pContext : Context)
    : RecyclerView.Adapter<RecentMovieAdapter.RecentMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentMovieViewHolder {
        val lLayout = LayoutInflater.from(parent.context)
        val lRecentMovieView : HomeScreenLayoutBinding = DataBindingUtil.inflate(
            lLayout,
            R.layout.home_screen_layout,
            parent,
            false
        )
        return  RecentMovieViewHolder(lRecentMovieView, pContext)
    }

    override fun onBindViewHolder(holder: RecentMovieViewHolder, position: Int) {
        holder.bind(pRecentMovieList[position])
    }

    override fun getItemCount(): Int = pRecentMovieList.size

    class RecentMovieViewHolder(private val binder: HomeScreenLayoutBinding, private val pContext: Context)
        : RecyclerView.ViewHolder(binder.root) {
        fun bind(pMovie : Results) {
            Glide.with(pContext)
                .load("https://image.tmdb.org/t/p/w500${pMovie.posterPath}")
                .centerCrop()
                .into(binder.movieImage)
        }
    }
}