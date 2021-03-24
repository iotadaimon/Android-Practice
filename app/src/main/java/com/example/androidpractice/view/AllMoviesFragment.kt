package com.example.androidpractice.view

import android.os.Bundle
import android.widget.Toast
import com.example.androidpractice.Constants
import com.example.androidpractice.MovieView
import com.example.androidpractice.R
import com.example.androidpractice.model.web.TMDBModel
import com.example.androidpractice.model.web.TMDBService
import com.example.androidpractice.presenter.AllMoviesPresenter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AllMoviesFragment : MovieListFragment(), MovieView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = AllMoviesPresenter(view = this)
    }

    override fun showErrorToast() {
        Toast.makeText(
            context,
            resources.getText(R.string.tmdb_api_response_error_message),
            Toast.LENGTH_SHORT
        ).show()
    }

}

