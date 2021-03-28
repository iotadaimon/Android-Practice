package com.example.androidpractice.model.local

import android.graphics.Bitmap
import com.example.androidpractice.Constants
import com.example.androidpractice.MutableMovieModel
import com.example.androidpractice.model.entity.Movie
import com.example.androidpractice.model.web.TMDBModel
import com.squareup.picasso.Picasso
import java.io.IOException

class LocalStorageModel(private val movieDAO: MovieDAO) : MutableMovieModel {

    private val picasso: Picasso = Picasso.get()

    override suspend fun addMovie(movie: Movie) = movieDAO.insertAll(movie)

    override suspend fun removeMovie(movie: Movie) = movieDAO.delete(movie)

    override suspend fun getMovies(pageNumber: Int): List<Movie> {
        val startIndex = (pageNumber - 1) * Constants.MOVIE_LIST_PAGE_SIZE

        return movieDAO.loadAllBetweenRows(startIndex.toLong(), Constants.MOVIE_LIST_PAGE_SIZE)
    }

    override suspend fun getMoviesById(id: Int): List<Movie> = movieDAO.loadAllById(id)

    @Throws(IOException::class)
    override suspend fun getMoviePoster(movie: Movie): Bitmap? =
        picasso.load("${TMDBModel.POSTER_DIR_BASE_URI}${movie.posterPath}").get()

}