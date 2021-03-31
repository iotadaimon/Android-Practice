package com.example.androidpractice.presenter

import com.example.androidpractice.Constants
import com.example.androidpractice.MovieDetailsContract
import com.example.androidpractice.MutableMovieModel
import com.example.androidpractice.model.entity.Movie
import com.example.androidpractice.model.local.LocalStorageModel
import com.example.androidpractice.model.local.MovieDatabaseSingleton
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailsPresenter : MovieDetailsContract.Presenter {

    private val model: MutableMovieModel = LocalStorageModel(
        MovieDatabaseSingleton.movieDAO,
        Constants.POSTER_SOURCE_URI
    )

    private lateinit var view: MovieDetailsContract.View

    override fun attachView(view: MovieDetailsContract.View) {
        this.view = view
    }

    // TODO - handle error cases
    override fun presentMovieDetails(movie: Movie) {
        val movieProperties = movie.getMovieProperties()

        model.getMoviePosterRx(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { posterBitmap ->
                view.showMovieDetails(posterBitmap, movieProperties)
            }
    }

    override fun presentLikedStatus(movie: Movie) {
        checkIfLikedRx(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isLiked ->
                view.showLikedStatus(isLiked)
            }
    }

    private fun checkIfLikedRx(movie: Movie): Single<Boolean> =
        model.getMoviesByIdRx(movie.id ?: -1)
            .map { list -> list.isNotEmpty() }

    override fun toggleLikedMovie(movie: Movie) {
        checkIfLikedRx(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isLiked ->
                when (isLiked) {
                    true -> deleteLikedMovie(movie)
                    false -> addLikedMovie(movie)
                }
                view.showLikedStatus(!isLiked)
            }
    }

    private fun deleteLikedMovie(movie: Movie) {
        model.removeMovieRx(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { view.showDeletedMovieMessage() }
    }

    private fun addLikedMovie(movie: Movie) {
        model.addMovieRx(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { view.showAddedMovieMessage() }
    }

}

/**
 * Extract and return the list of properties from a [Movie] instance.
 */
private fun Movie.getMovieProperties(): Map<String, Any?> = linkedMapOf(
    "Adult" to this.isAdult,
    "Overview" to this.overview,
    "Release Date" to this.releaseDate,
    "ID" to this.id,
    "Genre IDs" to this.genreIDs,
    "Original Title" to this.originalTitle,
    "Language" to this.originalLanguage,
    "Title" to this.title,
    "Backdrop Path" to this.backdropPath,
    "Popularity" to this.popularity,
    "Vote Vount" to this.voteCount,
    "Video" to this.video,
    "Vote Average" to this.voteAverage
)