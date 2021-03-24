package com.example.androidpractice.presenter

import com.example.androidpractice.MovieModel
import com.example.androidpractice.MoviePresenter
import com.example.androidpractice.MovieView
import com.example.androidpractice.model.entity.Movie
import com.example.androidpractice.model.local.LocalStorageModel
import com.example.androidpractice.model.local.MovieDatabaseSingleton
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.IOException

class LikedMoviesPresenter(
    private val model: MovieModel = defaultModel,
    private val view: MovieView,
    private val coroutineScope: CoroutineScope = GlobalScope
) : MoviePresenter {

    private companion object {
        val defaultModel: MovieModel
            get() = LocalStorageModel(MovieDatabaseSingleton.movieDAO)
    }

    private val mutex: Mutex = Mutex()

    private var lastLoadedPageNumber = 0
    private var movies: MutableList<Movie> = mutableListOf()

    override fun presentMovies(upToPageNumber: Int, refresh: Boolean) {
        coroutineScope.launch(Dispatchers.Main) {
            view.showProgressIndicator()

            mutex.withLock {
                if (refresh) clearCache()

                if (lastLoadedPageNumber < upToPageNumber) {
                    for (pageNumber in (lastLoadedPageNumber + 1)..upToPageNumber) {
                        val moviePage =
                            loadMovies(pageNumber).await() // TODO - Throw exception and handle here
                        movies.addAll(moviePage)
                    }
                    lastLoadedPageNumber = upToPageNumber
                }
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

    private fun clearCache() {
        lastLoadedPageNumber = 0
        movies = mutableListOf()
    }

}