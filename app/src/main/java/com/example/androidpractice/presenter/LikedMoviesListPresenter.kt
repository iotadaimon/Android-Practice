package com.example.androidpractice.presenter

import com.example.androidpractice.Constants
import com.example.androidpractice.model.local.LocalStorageModel
import com.example.androidpractice.model.local.MovieDatabaseSingleton

class LikedMoviesListPresenter : AbstractMovieListPresenter() {

    init {
        model = LocalStorageModel(MovieDatabaseSingleton.movieDAO, Constants.POSTER_SOURCE_URI)
    }

}