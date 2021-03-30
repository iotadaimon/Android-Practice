package com.example.androidpractice

import android.graphics.Bitmap
import com.example.androidpractice.model.entity.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface MovieModel {

    fun getMoviePageRx(pageNumber: Int = 1): Single<List<Movie>>

    fun getMoviesByIdRx(id: Int): Single<List<Movie>> {
        TODO("Not yet implemented")
    }

    fun getMoviePosterRx(movie: Movie): Single<Bitmap?> {
        TODO("Not yet implemented")
    }

}

interface MovieView {

    fun showMovies(movies: List<Movie>)

    fun showMovieDetails(movie: Movie)

    fun showProgressIndicator() {
        TODO("Not yet implemented")
    }

    fun hideProgressIndicator() {
        TODO("Not yet implemented")
    }

    fun showErrorToast() {
        TODO("Not yet implemented")
    }

}

interface MoviePresenter {

    fun attachView(view: MovieView)

    fun presentMovies(upToPageNumber: Int = 1, refresh: Boolean = false)

    fun getMoviePoster(movie: Movie): Bitmap {
        TODO("Not yet implemented")
    }

}

/**
 *  Movie Details
 */

interface MovieDetailsView {
    fun showMovieDetails(posterBitmap: Bitmap?, properties: Map<String, Any?>)
    fun showAddedMovieMessage()
    fun showDeletedMovieMessage()
    fun toggleLikedMovie(movie: Movie)
}

interface MovieDetailsPresenter {
    fun attachView(view: MovieDetailsView)
    fun presentMovieDetails(movie: Movie)
    fun toggleLikedMovie(movie: Movie)
    fun checkIfLikedRx(movie: Movie): Single<Boolean>
}

interface MutableMovieModel : MovieModel {
    fun addMovieRx(movie: Movie) : Completable
    fun removeMovieRx(movie: Movie) : Completable
}
