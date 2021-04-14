package com.example.androidpractice.movielist.view

import com.example.androidpractice.MovieListContract
import com.example.androidpractice.R
import com.example.androidpractice.movielist.view.base.AbstractMovieListFragment
import javax.inject.Inject
import javax.inject.Named

class LikedMoviesFragment : AbstractMovieListFragment(), MovieListContract.View {

    @Inject
    @Named("local_presenter")
    override lateinit var presenter: MovieListContract.Presenter

    override fun onStart() {
        super.onStart()
        presenter.presentMovies(refresh = true)
    }

    override fun showErrorToast() = showToast(getString(R.string.local_database_error_message))
}