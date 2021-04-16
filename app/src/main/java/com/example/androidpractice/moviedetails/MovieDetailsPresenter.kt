package com.example.androidpractice.moviedetails

import com.example.androidpractice.MovieDetailsContract
import com.example.androidpractice.MutableMovieModel
import com.example.androidpractice.model.entity.Movie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailsPresenter @Inject constructor() : MovieDetailsContract.Presenter {

    @Inject
    lateinit var model: MutableMovieModel

    private lateinit var view: MovieDetailsContract.View

    override fun attachView(view: MovieDetailsContract.View) {
        this.view = view
    }

    override fun presentMovieDetails(movie: Movie) {
        val movieProperties = movie.getMovieProperties()

        model.getMoviePosterRx(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { moviePosterBitmap, throwable ->
                if (throwable != null) view.showErrorToast()
                view.showMovieDetails(moviePosterBitmap, movieProperties)
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
    "Title" to this.title,
    "Original Title" to this.originalTitle,
    "Overview" to this.overview,
    "Release Date" to this.releaseDate,
    "Language" to this.originalLanguage,
    "Adult" to if (this.isAdult == true) "yes" else "no",
    "Vote Average" to this.voteAverage,
    "Vote Count" to this.voteCount
)