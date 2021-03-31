package com.example.androidpractice

import android.graphics.Bitmap
import com.example.androidpractice.model.entity.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * Models
 */

interface MovieModel {

    fun getMoviePageRx(pageNumber: Int = 1): Single<List<Movie>>

    fun getMoviesByIdRx(id: Int): Single<List<Movie>> {
        TODO("Not yet implemented")
    }

    fun getMoviePosterRx(movie: Movie): Single<Bitmap?>

}

interface MutableMovieModel : MovieModel {

    fun addMovieRx(movie: Movie): Completable

    fun removeMovieRx(movie: Movie): Completable

}

/**
 *  Movie List
 */

interface MovieListContract {

    interface View {

        fun showMovies(movies: List<Movie>)

        fun showMovieDetails(movie: Movie)

        fun showProgressIndicator()

        fun hideProgressIndicator()

        fun showErrorToast()

    }

    interface Presenter {

        fun attachView(view: View)

        fun presentMovies(upToPageNumber: Int = 1, refresh: Boolean = false)

    }

}

/**
 *  Movie Details
 */

interface MovieDetailsContract {

    interface View {

        fun showMovieDetails(posterBitmap: Bitmap?, properties: Map<String, Any?>)

        fun showLikedStatus(isLiked: Boolean)

        fun toggleLikedMovie(movie: Movie)

        fun showAddedMovieMessage()

        fun showDeletedMovieMessage()

    }

    interface Presenter {

        fun attachView(view: View)

        fun presentMovieDetails(movie: Movie)

        fun presentLikedStatus(movie: Movie)

        fun toggleLikedMovie(movie: Movie)

    }

}
