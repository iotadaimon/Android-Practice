package com.example.androidpractice.movielist.view.base

import androidx.recyclerview.widget.DiffUtil
import com.example.androidpractice.model.entity.Movie

class DiffCallback(private val oldMovies: List<Movie>, private val newMovies: List<Movie>) :
    DiffUtil.Callback() {

    override fun getOldListSize() = oldMovies.size

    override fun getNewListSize() = newMovies.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldMovies[oldItemPosition].id == newMovies[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldMovies[oldItemPosition] == newMovies[newItemPosition]

}