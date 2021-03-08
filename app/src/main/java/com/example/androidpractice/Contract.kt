package com.example.androidpractice

import com.example.androidpractice.model.TMDBService
import com.example.androidpractice.model.entity.Movie

interface Contract {

    interface TMDBModel {
        fun getPopularMovies(
            apiKey: String,
            language: String = TMDBService.DEFAULT_LANGUAGE_ARGUMENT,
            page: Int = TMDBService.DEFAULT_PAGE_ARGUMENT,
            region: String = TMDBService.DEFAULT_REGION_ARGUMENT
        ): List<Movie>
    }

    interface LikedMoviesModel {
        fun getLikedMovies(): List<Movie>
        fun addLikedMovie(movie: Movie)
        fun deleteLikedMovie(movie: Movie)
    }

    interface View {
        fun showAllMovies(movies: List<Movie>)
        fun showLikedMovies(movies: List<Movie>)
    }

    interface Presenter {
        fun presentAllMovies()
        fun presentLikedMovies()
        fun addLikedMovie(movie: Movie)
        fun deleteLikedMovie(movie: Movie)
    }

}