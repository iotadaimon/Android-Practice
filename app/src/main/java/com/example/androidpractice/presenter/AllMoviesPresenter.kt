package com.example.androidpractice.presenter

import com.example.androidpractice.MovieModel
import com.example.androidpractice.MoviePresenter
import com.example.androidpractice.MovieView
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.async


class AllMoviesPresenter(private val model: MovieModel, private val view: MovieView) :
    MoviePresenter {

    // TODO - Accept coroutine context as a variable
    override fun presentMovies() {
        view.showProgressIndicator()

        val handler = CoroutineExceptionHandler { _, exception ->
            view.showErrorToast()
            view.hideProgressIndicator()
        }

        GlobalScope.launch(handler) {
            launch(Dispatchers.Main) {
                val movies = GlobalScope.async { model.getMovies() }.await()

                view.hideProgressIndicator()
                view.showMovies(movies)
            }
        }
    }

}