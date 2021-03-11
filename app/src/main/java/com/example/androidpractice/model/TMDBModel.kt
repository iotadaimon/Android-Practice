package com.example.androidpractice.model

import com.example.androidpractice.Contract
import com.example.androidpractice.model.entity.Movie
import java.io.IOException
import kotlin.jvm.Throws

class TMDBModel(val tmdbService: TMDBService) : Contract.TMDBModel {

    companion object {
        const val API_KEY: String = "bd1a81fe362d2ec1258025a8ceb7a552" // Sensitive data
    }

    @Throws(IOException::class)
    override fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int,
        region: String
    ): List<Movie> {
        val response = tmdbService.getPopularMovies(apiKey).execute()
        if (response.isSuccessful) {
            return response.body()?.results ?: emptyList()
        } else {
            throw IOException()
        }
    }

}