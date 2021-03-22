package com.example.androidpractice.model.web

import com.example.androidpractice.MovieModel
import com.example.androidpractice.model.entity.Movie
import java.io.IOException
import kotlin.jvm.Throws

class TMDBModel(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : MovieModel {

    @Throws(IOException::class)
    override suspend fun getMovies(pageNumber: Int): List<Movie> {
        val response = tmdbService.getPopularMovies(apiKey, page = pageNumber).execute()
        if (response.isSuccessful) {
            return response.body()?.results ?: emptyList()
        } else {
            throw IOException()
        }
    }

}