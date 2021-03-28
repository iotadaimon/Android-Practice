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

    private val picasso: Picasso = Picasso.get()

    companion object {
        const val POSTER_DIR_BASE_URI: String = "https://image.tmdb.org/t/p/w500"
    }

    @Throws(IOException::class)
    override suspend fun getMovies(pageNumber: Int): List<Movie> {
        val response = tmdbService.getPopularMovies(apiKey, page = pageNumber).execute()
        if (response.isSuccessful) {
            return response.body()?.results ?: emptyList()
        } else {
            throw IOException()
        }
    }

    @Throws(IOException::class)
    override suspend fun getMoviePoster(movie: Movie): Bitmap? =
        picasso.load("$POSTER_DIR_BASE_URI${movie.posterPath}").get()

}