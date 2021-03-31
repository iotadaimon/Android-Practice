package com.example.androidpractice.view

import android.os.Bundle
import com.example.androidpractice.MovieListView
import com.example.androidpractice.R
import com.example.androidpractice.presenter.AllMoviesListPresenter

class AllMoviesFragment : AbstractMovieListFragment(), MovieListView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = AllMoviesListPresenter()
        presenter.attachView(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.presentMovies()
    }

    override fun showErrorToast() = showToast(getString(R.string.tmdb_api_response_error_message))

}

