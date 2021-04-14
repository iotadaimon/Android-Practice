package com.example.androidpractice.model.local

import android.graphics.Bitmap
import com.example.androidpractice.MutableMovieModel
import com.example.androidpractice.model.entity.Movie
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Named

class LocalStorageModel @Inject constructor(
    private val movieDAO: MovieDAO,
    @Named("poster_source_uri") private val posterDirPath: String,
    private val pageSize: Int
) : MutableMovieModel {

    @Inject
    lateinit var picasso: Picasso

    override fun addMovieRx(movie: Movie): Completable = movieDAO.insertAll(movie)

    override fun removeMovieRx(movie: Movie): Completable = movieDAO.delete(movie)

    override fun getMoviePageRx(pageNumber: Int): Single<List<Movie>> {
        val startIndex = (pageNumber - 1) * pageSize

        return movieDAO.loadRowsWithOffset(
            startIndex.toLong(),
            pageSize
        )
    }

    override fun getMoviesByIdRx(id: Int): Single<List<Movie>> = movieDAO.loadAllById(id)

    override fun getMoviePosterRx(movie: Movie): Single<Bitmap?> = Single.create {
        val bitmap = picasso.load("$posterDirPath${movie.posterPath}").get()
        it.onSuccess(bitmap)
    }
}