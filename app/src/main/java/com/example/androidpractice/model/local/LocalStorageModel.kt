package com.example.androidpractice.model.local

import com.example.androidpractice.MutableMovieModel
import com.example.androidpractice.model.entity.Movie

class LocalStorageModel : MutableMovieModel {

    override fun addMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun removeMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override suspend fun getMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

}