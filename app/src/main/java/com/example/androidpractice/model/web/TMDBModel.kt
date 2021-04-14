package com.example.androidpractice.model.web

import android.graphics.Bitmap
import com.example.androidpractice.MovieModel
import com.example.androidpractice.model.entity.Movie
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Named

class TMDBModel @Inject constructor(
    private val tmdbService: TMDBService,
    @Named("tmdb_api_key") private val apiKey: String,
    @Named("poster_source_uri") private val posterDirPath: String
) : MovieModel {

    @Inject
    lateinit var picasso: Picasso

    override fun getMoviePageRx(pageNumber: Int): Single<List<Movie>> =
        tmdbService.getPopularMovies(apiKey, page = pageNumber)
            .map { response -> response.results }

    override fun getMoviePosterRx(movie: Movie): Single<Bitmap?> = Single.create {
        val bitmap = picasso.load("$posterDirPath${movie.posterPath}").get()
        it.onSuccess(bitmap)
    }
}