package com.example.androidpractice.presenter

import com.example.androidpractice.MovieModel
import com.example.androidpractice.MoviePresenter
import com.example.androidpractice.MovieView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.io.IOException

class AllMoviesPresenter(private val model: MovieModel, private val view: MovieView) :
    MoviePresenter {

    // TODO - Accept coroutine context as a variable
    override fun presentMovies() {
        val movies = GlobalScope.async {
            try {
                model.getMovies()
            } catch (ex: IOException) {
                view.showErrorToast()
                emptyList()
            }
        }

        // TODO - implement a progress bar in the view

        runBlocking {
            view.showMovies(movies.await())
        }
    }

}