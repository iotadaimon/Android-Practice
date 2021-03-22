package com.example.androidpractice

import com.example.androidpractice.model.entity.Movie


interface MovieModel {
    suspend fun getMovies(pageNumber: Int = 1): List<Movie>
}

interface MutableMovieModel : MovieModel {
    fun addMovie(movie: Movie)
    fun removeMovie(movie: Movie)
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
    fun presentMovies(upToPageNumber: Int = 1)
}

interface MutableMoviePresenter : MoviePresenter {
    fun addLikedMovie(movie: Movie)
    fun deleteLikedMovie(movie: Movie)
}
