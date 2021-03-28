package com.example.androidpractice.presenter

import com.example.androidpractice.Constants
import com.example.androidpractice.model.web.TMDBModel
import com.example.androidpractice.model.web.TMDBService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AllMoviesPresenter(
    coroutineScope: CoroutineScope = GlobalScope
) : AbstractMovieListPresenter(coroutineScope) {

    init {
        val tmdbService = Retrofit.Builder()
            .baseUrl(TMDBService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TMDBService::class.java)

        model = TMDBModel(tmdbService, Constants.API_KEY)
    }

}