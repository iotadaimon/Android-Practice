package com.example.androidpractice.view

import android.os.Bundle
import android.widget.Toast
import com.example.androidpractice.MovieView
import com.example.androidpractice.R
import com.example.androidpractice.presenter.AllMoviesPresenter

class AllMoviesFragment : MovieListFragment(), MovieView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = AllMoviesPresenter(view = this)
    }

    override fun onStart() {
        super.onStart()
        presenter.presentMovies()
    }

    override fun showErrorToast() {
        Toast.makeText(
            context,
            resources.getText(R.string.tmdb_api_response_error_message),
            Toast.LENGTH_SHORT
        ).show()
    }

}

