package com.example.androidpractice.presenter

import com.example.androidpractice.MovieDetailsPresenter
import com.example.androidpractice.MovieDetailsView
import com.example.androidpractice.MutableMovieModel
import com.example.androidpractice.model.entity.Movie
import com.example.androidpractice.model.local.LocalStorageModel
import com.example.androidpractice.model.local.MovieDatabaseSingleton
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailsPresenterImpl : MovieDetailsPresenter {

    protected val model: MutableMovieModel // Assign model on instantiation
    protected lateinit var view: MovieDetailsView

    init {
        model = LocalStorageModel(MovieDatabaseSingleton.movieDAO)
    }

    override fun attachView(view: MovieDetailsView) {
        this.view = view
    }

    override fun presentMovieDetails(movie: Movie) {
        val moviePosterSingle = model.getMoviePosterRx(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        moviePosterSingle.subscribe { posterBitmap ->
            val movieProperties = movie.getMovieProperties()
            view.showMovieDetails(posterBitmap, movieProperties)
        }

    }

    override fun toggleLikedMovie(movie: Movie) {
        val movieLikedStatusSingle = checkIfLikedRx(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        movieLikedStatusSingle.subscribe { isLiked ->
            when (isLiked) {
                true -> deleteLikedMovie(movie)
                false -> addLikedMovie(movie)
            }
        }
    }

    override fun checkIfLikedRx(movie: Movie): Single<Boolean> =
        model.getMoviesByIdRx(movie.id ?: -1)
            .map { list -> list.isNotEmpty() }

    private fun addLikedMovie(movie: Movie) {
        model.addMovieRx(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { view.showAddedMovieMessage() }
    }

    private fun deleteLikedMovie(movie: Movie) {
        model.removeMovieRx(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { view.showDeletedMovieMessage() }
    }

}

// Extracts and returns a list of properties from a Movie instance
private fun Movie.getMovieProperties(): Map<String, Any?> = linkedMapOf(
    "Adult" to this.adult,
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