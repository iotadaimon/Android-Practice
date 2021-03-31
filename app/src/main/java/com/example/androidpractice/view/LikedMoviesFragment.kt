package com.example.androidpractice.view

import android.os.Bundle
import com.example.androidpractice.MovieListView
import com.example.androidpractice.R
import com.example.androidpractice.presenter.LikedMoviesListPresenter

class LikedMoviesFragment : AbstractMovieListFragment(), MovieListView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = LikedMoviesListPresenter()
        presenter.attachView(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.presentMovies(refresh = true)
    }

    override fun showErrorToast() = showToast(getString(R.string.local_database_error_message))

}