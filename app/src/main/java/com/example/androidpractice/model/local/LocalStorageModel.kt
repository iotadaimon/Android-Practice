package com.example.androidpractice.model.local

import com.example.androidpractice.Constants
import com.example.androidpractice.MutableMovieModel
import com.example.androidpractice.model.entity.Movie

class LocalStorageModel(private val movieDAO: MovieDAO) : MutableMovieModel {

    override suspend fun addMovie(movie: Movie) = movieDAO.insertAll(movie)

    override suspend fun removeMovie(movie: Movie) = movieDAO.delete(movie)

    override suspend fun getMovies(pageNumber: Int): List<Movie> {
        val startIndex = (pageNumber - 1) * Constants.MOVIE_LIST_PAGE_SIZE

        return movieDAO.loadAllBetweenRows(startIndex.toLong(), Constants.MOVIE_LIST_PAGE_SIZE)
    }

    override suspend fun getMoviesById(id: Int): List<Movie> = movieDAO.loadAllById(id)

}