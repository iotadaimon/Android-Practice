package com.example.androidpractice.movielist.view

import com.example.androidpractice.MovieListContract
import com.example.androidpractice.R
import com.example.androidpractice.dagger.LocalMoviePresenter
import com.example.androidpractice.movielist.view.base.AbstractMovieListFragment
import javax.inject.Inject

class LikedMoviesFragment : AbstractMovieListFragment(), MovieListContract.View {

    @Inject
    @LocalMoviePresenter
    override lateinit var presenter: MovieListContract.Presenter

    override fun onStart() {
        super.onStart()
        presenter.presentMovies(refresh = true)
    }

    override fun showErrorToast() = showToast(getString(R.string.local_database_error_message))
}