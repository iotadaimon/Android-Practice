package com.example.androidpractice.presenter

import com.example.androidpractice.model.local.LocalStorageModel
import com.example.androidpractice.model.local.MovieDatabaseSingleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope

class LikedMoviesPresenter(
    coroutineScope: CoroutineScope = GlobalScope
) : AbstractMovieListPresenter(coroutineScope) {

    init {
        model = LocalStorageModel(MovieDatabaseSingleton.movieDAO)
    }

}