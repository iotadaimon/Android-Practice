package com.example.androidpractice

import android.view.View
import com.example.androidpractice.model.entity.Movie


interface MovieModel {
    suspend fun getMovies(): List<Movie>
}

interface MutableMovieModel : MovieModel {
    fun addMovie(movie: Movie)
    fun removeMovie(movie: Movie)
}

interface MovieView {

    fun showMovies(movies: List<Movie>)

    fun showMovieDetails(movie: Movie)

    fun showErrorToast() {
        TODO("Not yet implemented")
    }

}

interface MoviePresenter {
    fun presentMovies()
}

interface LikedMoviePresenter : MoviePresenter {
    fun addLikedMovie(movie: Movie)
    fun deleteLikedMovie(movie: Movie)
}
