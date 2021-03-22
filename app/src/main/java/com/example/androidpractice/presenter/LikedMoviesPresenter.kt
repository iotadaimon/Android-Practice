package com.example.androidpractice.presenter

import com.example.androidpractice.MovieModel
import com.example.androidpractice.MoviePresenter
import com.example.androidpractice.MovieView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope

class LikedMoviesPresenter(
    private val model: MovieModel,
    private val view: MovieView,
    private val coroutineScope: CoroutineScope = GlobalScope
) : MoviePresenter {

    override fun presentMovies(upToPageNumber: Int) {
        TODO("Not yet implemented")
    }

}