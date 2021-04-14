package com.example.androidpractice.model.web

import android.graphics.Bitmap
import com.example.androidpractice.MovieModel
import com.example.androidpractice.dagger.ApiKey
import com.example.androidpractice.dagger.PosterRepositoryBaseUrl
import com.example.androidpractice.model.entity.Movie
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class TMDBModel @Inject constructor(
    private val tmdbService: TMDBService,
    @ApiKey private val apiKey: String,
    @PosterRepositoryBaseUrl private val posterRepoBaseUrl: String
) : MovieModel {

    @Inject
    lateinit var picasso: Picasso

    override fun getMoviePageRx(pageNumber: Int): Single<List<Movie>> =
        tmdbService.getPopularMovies(apiKey, page = pageNumber)
            .map { response -> response.results }

    override fun getMoviePosterRx(movie: Movie): Single<Bitmap?> = Single.create {
        val bitmap = picasso.load("$posterRepoBaseUrl${movie.posterPath}").get()
        it.onSuccess(bitmap)
    }
}