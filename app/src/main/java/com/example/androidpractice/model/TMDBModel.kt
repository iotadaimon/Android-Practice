package com.example.androidpractice.model

import com.example.androidpractice.MovieModel
import com.example.androidpractice.model.entity.Movie
import java.io.IOException
import kotlin.jvm.Throws

class TMDBModel(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : MovieModel {

    companion object {
        const val API_KEY: String = "bd1a81fe362d2ec1258025a8ceb7a552" // TODO - Sensitive data
    }

    @Throws(IOException::class)
    override suspend fun getMovies(): List<Movie> {
        val response = tmdbService.getPopularMovies(apiKey).execute()
        if (response.isSuccessful) {
            return response.body()?.results ?: emptyList()
        } else {
            throw IOException()
        }
    }

}