package com.example.androidpractice.presenter

import android.graphics.Bitmap
import com.example.androidpractice.MovieDetailsPresenter
import com.example.androidpractice.MovieDetailsView
import com.example.androidpractice.MutableMovieModel
import com.example.androidpractice.model.entity.Movie
import com.example.androidpractice.model.local.LocalStorageModel
import com.example.androidpractice.model.local.MovieDatabaseSingleton
import kotlinx.coroutines.*

class MovieDetailsPresenterImpl(
    private val coroutineScope: CoroutineScope = GlobalScope
) : MovieDetailsPresenter {

    protected val model: MutableMovieModel // Assign model on instantiation
    protected lateinit var view: MovieDetailsView

    init {
        model = LocalStorageModel(MovieDatabaseSingleton.movieDAO)
    }

    override fun attachView(view: MovieDetailsView) {
        this.view = view
    }

    override fun presentMovieDetails(movie: Movie) {
        coroutineScope.launch(Dispatchers.IO) {
            val poster: Bitmap? = model.getMoviePoster(movie)
            val movieProperties = movie.getMovieProperties()
            launch(Dispatchers.Main) { view.showMovieDetails(poster, movieProperties) }
        }
    }

    override fun toggleLikedMovie(movie: Movie) = when (checkIfLiked(movie)) {
        true -> {
            deleteLikedMovie(movie)
            view.showDeletedMovieMessage()
        }
        false -> {
            addLikedMovie(movie)
            view.showAddedMovieMessage()
        }
    }

    // TODO - use coroutines better
    override fun checkIfLiked(movie: Movie): Boolean {
        return runBlocking {
            coroutineScope.async(Dispatchers.IO) {
                model.getMoviesById(movie.id ?: -1)
            }.await()
        }.isNotEmpty()
    }

    private fun addLikedMovie(movie: Movie) {
        coroutineScope.launch(Dispatchers.IO) { model.addMovie(movie) }
    }

    private fun deleteLikedMovie(movie: Movie) {
        coroutineScope.launch(Dispatchers.IO) { model.removeMovie(movie) }
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

}