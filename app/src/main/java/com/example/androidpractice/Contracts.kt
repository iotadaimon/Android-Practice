package com.example.androidpractice

import android.graphics.Bitmap
import com.example.androidpractice.model.entity.Movie

interface MovieModel {

    suspend fun getMovies(pageNumber: Int = 1): List<Movie>

    suspend fun getMoviesById(id: Int): List<Movie> {
        TODO("Not yet implemented")
    }

}

interface MutableMovieModel : MovieModel {
    suspend fun addMovie(movie: Movie)
    suspend fun removeMovie(movie: Movie)
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

interface MovieDetailsView {
    fun showMovieDetails(poster: Bitmap, properties: Map<String, Any?>)
    fun showAddedMovieMessage()
    fun showDeletedMovieMessage()
    fun toggleLikedMovie(movie: Movie)
}

interface MoviePresenter {
    fun presentMovies(upToPageNumber: Int = 1, refresh: Boolean = false)
}

interface MovieDetailsPresenter {
    fun presentMovieDetails(movie: Movie)
    fun toggleLikedMovie(movie: Movie)
    fun checkIfLiked(movie: Movie): Boolean
}
