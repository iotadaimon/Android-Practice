package com.example.androidpractice

import com.example.androidpractice.model.entity.Movie
import kotlinx.coroutines.Deferred


interface MovieModel {
    suspend fun getMovies(): List<Movie>
}

interface MutableMovieModel : MovieModel {
    fun addMovie(movie: Movie)
    fun removeMovie(movie: Movie)
}

interface MovieView {
    fun showMovies(movies: Deferred<List<Movie>>)

    fun showMovies(movies: List<Movie>) {
        TODO("Not yet implemented")
    }

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
