package com.example.androidpractice.presenter

import com.example.androidpractice.MovieModel
import com.example.androidpractice.MoviePresenter
import com.example.androidpractice.MovieView
import com.example.androidpractice.model.entity.Movie
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.IOException

abstract class AbstractMovieListPresenter(
    protected val coroutineScope: CoroutineScope = GlobalScope
) : MoviePresenter {

    protected lateinit var model: MovieModel // Assign model on instantiation
    protected lateinit var view: MovieView

    protected val mutex: Mutex = Mutex()

    protected var lastLoadedPageNumber = 0
    protected var movies: MutableList<Movie> = mutableListOf()

    override fun attachView(view: MovieView) {
        this.view = view
    }

    override fun presentMovies(upToPageNumber: Int, refresh: Boolean) {
        coroutineScope.launch(Dispatchers.Main) {
            mutex.withLock {
                view.showProgressIndicator()

                if (refresh) clearCache()

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
    }

    protected suspend fun loadMovies(pageNumber: Int): Deferred<List<Movie>> =
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

    protected fun clearCache() {
        lastLoadedPageNumber = 0
        movies = mutableListOf()
    }

}