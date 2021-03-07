package com.example.androidpractice

import com.example.androidpractice.model.entity.Movie

interface Contract {

    interface Model {
        fun getPopularMovies(apiKey: String, language: String = "en-US", page: Int = 1, region: String = ""): List<Movie>
    }

    interface View {
        // TODO
    }

    interface Presenter {
        // TODO
    }

}