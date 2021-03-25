package com.example.androidpractice.model.web

import android.graphics.Bitmap
import com.example.androidpractice.MovieModel
import com.example.androidpractice.model.entity.Movie
import com.squareup.picasso.Picasso
import java.io.IOException

class TMDBModel(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : MovieModel {

    @Throws(IOException::class)
    override suspend fun getMovies(pageNumber: Int): List<Movie> {
        val response = tmdbService.getPopularMovies(apiKey, page = pageNumber).execute()
        if (response.isSuccessful) {
            // TODO - put movie poster into persistent storage and assign path in movie instance
            return response.body()?.results ?: emptyList()
        } else {
            throw IOException()
        }
    }

    @Throws(IOException::class)
    override suspend fun getMoviePoster(movie: Movie): Bitmap =
        Picasso.get().load("https://image.tmdb.org/t/p/w500${movie.posterPath}").get()

}