package com.example.androidpractice

import com.example.androidpractice.model.TMDBService
import com.example.androidpractice.model.entity.Movie

interface Contract {

    interface Model {
        fun getPopularMovies(
                apiKey: String,
                language: String = TMDBService.DEFAULT_LANGUAGE_ARGUMENT,
                page: Int = TMDBService.DEFAULT_PAGE_ARGUMENT,
                region: String = TMDBService.DEFAULT_REGION_ARGUMENT
        ): List<Movie>
    }

    interface View {
        // TODO
    }

    interface Presenter {
        // TODO
    }

}