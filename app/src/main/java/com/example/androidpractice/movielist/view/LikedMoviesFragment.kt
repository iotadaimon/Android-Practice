package com.example.androidpractice.movielist.view

import android.os.Bundle
import com.example.androidpractice.MovieListContract
import com.example.androidpractice.R
import com.example.androidpractice.movielist.presenter.LikedMoviesListPresenter
import com.example.androidpractice.movielist.view.base.AbstractMovieListFragment

class LikedMoviesFragment : AbstractMovieListFragment(), MovieListContract.View {

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