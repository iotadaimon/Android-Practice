package com.example.androidpractice.model.local

import android.graphics.Bitmap
import com.example.androidpractice.Constants
import com.example.androidpractice.MutableMovieModel
import com.example.androidpractice.model.entity.Movie
import com.example.androidpractice.model.web.TMDBModel
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class LocalStorageModel(private val movieDAO: MovieDAO) : MutableMovieModel {

    private val picasso: Picasso = Picasso.get()

    override fun addMovieRx(movie: Movie): Completable = movieDAO.insertAll(movie)

    override fun removeMovieRx(movie: Movie): Completable = movieDAO.delete(movie)

    override fun getMoviePageRx(pageNumber: Int): Single<List<Movie>> {
        val startIndex = (pageNumber - 1) * Constants.MOVIE_LIST_PAGE_SIZE

        return movieDAO.loadAllBetweenRows(
            startIndex.toLong(),
            Constants.MOVIE_LIST_PAGE_SIZE
        )
    }

    override fun getMoviesByIdRx(id: Int): Single<List<Movie>> = movieDAO.loadAllById(id)

    override fun getMoviePosterRx(movie: Movie): Single<Bitmap?> = Single.create {
        val bitmap = picasso.load("${TMDBModel.POSTER_DIR_BASE_URI}${movie.posterPath}").get()
        it.onSuccess(bitmap)
    }

}