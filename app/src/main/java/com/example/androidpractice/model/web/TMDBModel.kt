package com.example.androidpractice.model.web

import android.graphics.Bitmap
import com.example.androidpractice.MovieModel
import com.example.androidpractice.model.entity.Movie
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.core.Single

class TMDBModel(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : MovieModel {

    private val picasso: Picasso = Picasso.get()

    companion object {
        const val POSTER_DIR_BASE_URI: String = "https://image.tmdb.org/t/p/w500"
    }

    override fun getMoviePageRx(pageNumber: Int): Single<List<Movie>> =
        tmdbService.getPopularMovies(apiKey, page = pageNumber)
            .map { response -> response.results }

    override fun getMoviePosterRx(movie: Movie): Single<Bitmap?> = Single.create {
        val bitmap = picasso.load("$POSTER_DIR_BASE_URI${movie.posterPath}").get()
        it.onSuccess(bitmap)
    }

}