package com.movie.movierecommendation.adapter

import android.database.DatabaseUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.movie.movierecommendation.R
import com.movie.movierecommendation.databinding.HomeScreenLayoutBinding

class RecentMovieAdapter(val pRecentMovieList : MutableList<String>) : RecyclerView.Adapter<RecentMovieAdapter.RecentMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentMovieViewHolder {
        val lLayout = LayoutInflater.from(parent.context)
        val lRecentMovieView : HomeScreenLayoutBinding = DataBindingUtil.inflate(
            lLayout,
            R.layout.home_screen_layout,
            parent,
            false
        )
        return  RecentMovieViewHolder(lRecentMovieView)
    }

    override fun onBindViewHolder(holder: RecentMovieViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 120

    class RecentMovieViewHolder(binder : HomeScreenLayoutBinding) : RecyclerView.ViewHolder(binder.root) {
        fun bind() {}
    }
}