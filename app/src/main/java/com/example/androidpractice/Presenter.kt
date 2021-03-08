package com.example.androidpractice

import com.example.androidpractice.model.entity.Movie
import java.io.Serializable

// TODO - replace Serializable with Parcelable and implement
class Presenter(
    private val tmdbModel: Contract.TMDBModel,
    private val likedMoviesModel: Contract.LikedMoviesModel,
    private var view: Contract.View? = null
) : Contract.Presenter, Serializable {

    override fun registerView(view: Contract.View) {
        TODO("Not yet implemented")
    }

    override fun presentAllMovies() {
        TODO("Not yet implemented")
    }

    override fun presentLikedMovies() {
        TODO("Not yet implemented")
    }

    override fun addLikedMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun deleteLikedMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

}