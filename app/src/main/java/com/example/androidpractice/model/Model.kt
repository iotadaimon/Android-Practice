package com.example.androidpractice.model

import com.example.androidpractice.Contract
import com.example.androidpractice.model.entity.Movie

class Model(val tmdbService: TMDBService) : Contract.Model {

    override fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int,
        region: String
    ): List<Movie> {
        TODO("Not yet implemented")
    }

}