package com.example.androidpractice.movielist.view

import android.os.Bundle
import com.example.androidpractice.MovieListContract
import com.example.androidpractice.R
import com.example.androidpractice.movielist.presenter.AllMoviesListPresenter

class AllMoviesFragment : AbstractMovieListFragment(), MovieListContract.View {

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
