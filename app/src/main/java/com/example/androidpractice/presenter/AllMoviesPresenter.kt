package com.example.androidpractice.presenter

import com.example.androidpractice.Constants
import com.example.androidpractice.MovieModel
import com.example.androidpractice.MovieView
import com.example.androidpractice.model.web.TMDBModel
import com.example.androidpractice.model.web.TMDBService
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AllMoviesPresenter(
    model: MovieModel = defaultModel,
    view: MovieView,
    coroutineScope: CoroutineScope = GlobalScope
) : AbstractMovieListPresenter(model, view, coroutineScope) {

    private companion object {
        val defaultModel: MovieModel
            get() {
                val tmdbService = Retrofit.Builder()
                    .baseUrl(TMDBService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(TMDBService::class.java)

                return TMDBModel(tmdbService, Constants.API_KEY)
            }
    }

}