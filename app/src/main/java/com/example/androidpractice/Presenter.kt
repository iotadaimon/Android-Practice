package com.example.androidpractice

import com.example.androidpractice.model.TMDBModel
import com.example.androidpractice.model.entity.Movie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class Presenter(
    private val tmdbModel: Contract.TMDBModel,
    private val likedMoviesModel: Contract.LikedMoviesModel,
    private val view: Contract.View
) : Contract.Presenter {

    override fun registerView(view: Contract.View) {
        TODO("Not yet implemented")
    }

    // TODO - Perform requests asynchronously
    // TODO - Pass a coroutine scope as an argument
    override fun presentAllMovies() {
        val movies = runBlocking {
            GlobalScope.async {
                tmdbModel.getPopularMovies(TMDBModel.API_KEY)
            }.await()
        }
        view.showAllMovies(movies)
    }

    override fun presentLikedMovies() {
        val movies = emptyList<Movie>() // TODO - Fetch movies from local storage

        view.showLikedMovies(movies)
    }

    override fun addLikedMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun deleteLikedMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

}