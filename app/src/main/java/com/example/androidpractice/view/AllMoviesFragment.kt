package com.example.androidpractice.view

import android.os.Bundle
import com.example.androidpractice.MovieView
import com.example.androidpractice.R
import com.example.androidpractice.presenter.AllMoviesPresenter

class AllMoviesFragment : AbstractMovieListFragment(), MovieView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = AllMoviesPresenter(view = this)
    }

    override fun onStart() {
        super.onStart()
        presenter.presentMovies()
    }

    override fun showErrorToast() = showToast(getString(R.string.tmdb_api_response_error_message))

}

