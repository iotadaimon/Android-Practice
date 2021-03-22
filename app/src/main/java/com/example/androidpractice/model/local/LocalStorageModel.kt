package com.example.androidpractice.model.local

import com.example.androidpractice.Constants
import com.example.androidpractice.MutableMovieModel
import com.example.androidpractice.model.entity.Movie

class LocalStorageModel(private val movieDAO: MovieDAO) : MutableMovieModel {

    private val moviesToServe: MutableList<Movie> = mutableListOf()

    override fun addMovie(movie: Movie) = movieDAO.insertAll(movie)

    override fun removeMovie(movie: Movie) = movieDAO.delete(movie)

    override suspend fun getMovies(pageNumber: Int): List<Movie> {
        if (moviesToServe.isEmpty()) moviesToServe.addAll(movieDAO.getAll())

        val startIndex = pageNumber * (Constants.MOVIE_LIST_PAGE_SIZE - 1)
        val endIndex = pageNumber * Constants.MOVIE_LIST_PAGE_SIZE - 1

        return moviesToServe.filterIndexed { index: Int, _ ->
            index in startIndex..endIndex
        }
    }

}