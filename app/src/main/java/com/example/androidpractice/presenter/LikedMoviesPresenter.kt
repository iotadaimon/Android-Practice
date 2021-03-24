package com.example.androidpractice.presenter

import com.example.androidpractice.MovieModel
import com.example.androidpractice.MovieView
import com.example.androidpractice.model.local.LocalStorageModel
import com.example.androidpractice.model.local.MovieDatabaseSingleton
import kotlinx.coroutines.*

class LikedMoviesPresenter(
    model: MovieModel = defaultModel,
    view: MovieView,
    coroutineScope: CoroutineScope = GlobalScope
) : AbstractMovieListPresenter(model, view, coroutineScope) {

    private companion object {
        val defaultModel: MovieModel
            get() = LocalStorageModel(MovieDatabaseSingleton.movieDAO)
    }

}