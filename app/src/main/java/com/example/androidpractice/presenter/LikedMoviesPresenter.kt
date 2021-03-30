package com.example.androidpractice.presenter

import com.example.androidpractice.model.local.LocalStorageModel
import com.example.androidpractice.model.local.MovieDatabaseSingleton

class LikedMoviesPresenter : AbstractMovieListPresenter() {

    init {
        model = LocalStorageModel(MovieDatabaseSingleton.movieDAO)
    }

}