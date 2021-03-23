package com.example.androidpractice.view

import android.os.Bundle
import android.widget.Toast
import com.example.androidpractice.MovieView
import com.example.androidpractice.R
import com.example.androidpractice.model.local.LocalStorageModel
import com.example.androidpractice.model.local.MovieDatabaseSingleton
import com.example.androidpractice.presenter.LikedMoviesPresenter

class LikedMoviesFragment : MovieListFragment(), MovieView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = LikedMoviesPresenter(LocalStorageModel(MovieDatabaseSingleton.movieDAO), this)
    }

    override fun showErrorToast() {
        Toast.makeText(
            context,
            resources.getText(R.string.tmdb_api_response_error_message),
            Toast.LENGTH_SHORT
        ).show()
    }

}