package com.example.androidpractice

import com.example.androidpractice.model.TMDBModel
import com.example.androidpractice.model.entity.Movie

class Presenter(
    private val tmdbModel: Contract.TMDBModel,
    private val likedMoviesModel: Contract.LikedMoviesModel,
    private val view: Contract.View
) : Contract.Presenter {

    override fun registerView(view: Contract.View) {
        TODO("Not yet implemented")
    }

    override fun presentAllMovies() {
        val movies = tmdbModel.getPopularMovies(TMDBModel.API_KEY) // TODO - launch in a separate thread
        view.showAllMovies(movies)
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