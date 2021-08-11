package com.movie.movierecommendation.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.movie.movierecommendation.R
import com.movie.movierecommendation.databinding.GenreLayoutBinding
import com.movie.movierecommendation.listeners.PositionListeners
import com.movie.movierecommendation.retrofit.pojo.GenreList
import com.movie.movierecommendation.room.table.GenreTable

class GenreAdapter(
    private val pGenreList: MutableList<GenreList>,
    private val pContext: Context,
    private val pPositionListeners: PositionListeners
    ) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val lLayout = LayoutInflater.from(parent.context)
        val lGenreLayout : GenreLayoutBinding = DataBindingUtil.inflate(
            lLayout,
            R.layout.genre_layout,
            parent,
            false
        )

        return  GenreViewHolder(lGenreLayout, pContext)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(pGenreList[position])
        holder.mCardView.setOnClickListener {
            pPositionListeners.getGenrePosition(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = pGenreList.size

    class GenreViewHolder(private val binder: GenreLayoutBinding, private val pContext: Context) : RecyclerView.ViewHolder(binder.root) {

         val mCardView = binder.mMovieGenre

         fun bind(pGenre : GenreList) {
             binder.mMovieGenre.text = pGenre.name
             if (pGenre.isSelectedGenre) {
                 binder.mMovieGenre.background = pContext.getDrawable(R.drawable.genre_rounded_background)
                 binder.mMovieGenre.setTextColor(Color.BLACK)
             } else {
                 binder.mMovieGenre.background = null
                 binder.mMovieGenre.setTextColor(Color.WHITE)
             }
         }
    }

}