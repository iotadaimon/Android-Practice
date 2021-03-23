package com.example.androidpractice.presenter

import com.example.androidpractice.MovieModel
import com.example.androidpractice.MoviePresenter
import com.example.androidpractice.MovieView
import com.example.androidpractice.model.entity.Movie
import kotlinx.coroutines.*
import java.io.IOException

class AllMoviesPresenter(
    private val model: MovieModel,
    private val view: MovieView,
    private val coroutineScope: CoroutineScope = GlobalScope
) : MoviePresenter {

    private var lastLoadedPageNumber = 0
    private val movies: MutableList<Movie> = mutableListOf()

    override fun presentMovies(upToPageNumber: Int) {
        coroutineScope.launch(Dispatchers.Main) {
            view.showProgressIndicator()

            if (lastLoadedPageNumber < upToPageNumber) {
                for (pageNumber in (lastLoadedPageNumber + 1)..upToPageNumber) {
                    val moviePage =
                        loadMovies(pageNumber).await() // TODO - Throw exception and handle here
                    movies.addAll(moviePage)
                }
                lastLoadedPageNumber = upToPageNumber
            }

            view.hideProgressIndicator()
            view.showMovies(movies)
        }
    }

    private suspend fun loadMovies(pageNumber: Int): Deferred<List<Movie>> =
        coroutineScope.async(Dispatchers.IO) {
            var movies = emptyList<Movie>()

            try {
                movies = model.getMovies(pageNumber)
            } catch (ex: IOException) {
                launch(Dispatchers.Main) {
                    view.hideProgressIndicator()
                    view.showErrorToast()
                }
            }

            movies
        }

}