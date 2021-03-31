package com.example.androidpractice.presenter

import com.example.androidpractice.Constants
import com.example.androidpractice.model.web.TMDBModel
import com.example.androidpractice.model.web.TMDBService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AllMoviesListPresenter : AbstractMovieListPresenter() {

    init {
        val tmdbService = Retrofit.Builder()
            .baseUrl(TMDBService.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TMDBService::class.java)

        model = TMDBModel(tmdbService, Constants.API_KEY, Constants.POSTER_SOURCE_URI)
    }

}