package com.example.androidpractice.presenter

import com.example.androidpractice.MovieModel
import com.example.androidpractice.MoviePresenter
import com.example.androidpractice.MovieView
import kotlinx.coroutines.*


class AllMoviesPresenter(
    private val model: MovieModel,
    private val view: MovieView,
    private val coroutineScope: CoroutineScope = GlobalScope
) : MoviePresenter {

    override fun presentMovies() {
        view.showProgressIndicator()

        val handler = CoroutineExceptionHandler { _, _ ->
            view.hideProgressIndicator()
            view.showErrorToast()
        }

        coroutineScope.launch(handler) {
            launch(Dispatchers.Main) {
                val movies = coroutineScope.async { model.getMovies() }.await()

                view.hideProgressIndicator()
                view.showMovies(movies)
            }
        }
    }

}