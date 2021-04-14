package com.example.androidpractice.movielist.view

import com.example.androidpractice.MovieListContract
import com.example.androidpractice.R
import com.example.androidpractice.dagger.WebMoviePresenter
import com.example.androidpractice.movielist.view.base.AbstractMovieListFragment
import javax.inject.Inject

class AllMoviesFragment : AbstractMovieListFragment(), MovieListContract.View {

    @Inject
    @WebMoviePresenter
    override lateinit var presenter: MovieListContract.Presenter

    override fun onStart() {
        super.onStart()
        presenter.presentMovies()
    }

    override fun showErrorToast() = showToast(getString(R.string.tmdb_api_response_error_message))
}
