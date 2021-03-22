package com.example.androidpractice.presenter

import com.example.androidpractice.MovieView
import com.example.androidpractice.MutableMovieModel
import com.example.androidpractice.MutableMoviePresenter
import com.example.androidpractice.model.entity.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope

class MovieDetailsPresenter(
    private val model: MutableMovieModel,
    private val view: MovieView,
    private val coroutineScope: CoroutineScope = GlobalScope
) : MutableMoviePresenter {

    override fun addLikedMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun deleteLikedMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun presentMovies(upToPageNumber: Int) {
        TODO("Not yet implemented")
    }

}