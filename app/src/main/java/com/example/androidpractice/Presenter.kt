package com.example.androidpractice

import com.example.androidpractice.model.entity.Movie

class Presenter(
    private val tmdbModel: Contract.TMDBModel,
    private val likedMoviesModel: Contract.LikedMoviesModel,
    private val view: Contract.View
) : Contract.Presenter {

    override fun presentAllMovies() {
        TODO("Not yet implemented")
    }

    override fun presentLikedMovies() {
        TODO("Not yet implemented")
    }

    override fun addLikedMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun deleteLikedMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

}