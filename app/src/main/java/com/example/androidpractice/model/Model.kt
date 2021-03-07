package com.example.androidpractice.model

import com.example.androidpractice.Contract
import com.example.androidpractice.model.entity.Movie
import java.io.IOException
import kotlin.jvm.Throws

class Model(val tmdbService: TMDBService) : Contract.Model {

    private companion object {
        const val API_KEY: String = "bd1a81fe362d2ec1258025a8ceb7a552"
    }

    @Throws(IOException::class)
    override fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int,
        region: String
    ): List<Movie> {
        val response = tmdbService.getPopularMovies(API_KEY).execute()
        if (response.isSuccessful) {
            return response.body()?.results ?: emptyList()
        } else {
            throw IOException()
        }
    }

}