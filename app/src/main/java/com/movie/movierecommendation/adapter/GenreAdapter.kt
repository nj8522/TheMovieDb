package com.movie.movierecommendation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.movie.movierecommendation.R
import com.movie.movierecommendation.databinding.GenreLayoutBinding

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val lLayout = LayoutInflater.from(parent.context)
        val lGenreLayout : GenreLayoutBinding = DataBindingUtil.inflate(
            lLayout,
            R.layout.genre_layout,
            parent,
            false
        )

        return  GenreViewHolder(lGenreLayout)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 8

    class GenreViewHolder(binder : GenreLayoutBinding) : RecyclerView.ViewHolder(binder.root) {
         fun bind() {}
    }

}