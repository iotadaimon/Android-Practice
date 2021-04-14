package com.example.androidpractice.dagger

import com.example.androidpractice.MovieListContract
import com.example.androidpractice.MovieModel
import com.example.androidpractice.movielist.presenter.MovieListPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object MovieListPresenterModule {

    @Singleton
    @Provides
    @WebMoviePresenter
    fun provideAllMoviesPresenter(
        movieListPresenter: MovieListPresenter,
        @WebModel model: MovieModel
    ): MovieListContract.Presenter = movieListPresenter.apply { this.model = model }

    @Singleton
    @Provides
    @LocalMoviePresenter
    fun provideLikedMoviesPresenter(
        movieListPresenter: MovieListPresenter,
        @LocalImmutableModel model: MovieModel
    ): MovieListContract.Presenter = movieListPresenter.apply { this.model = model }
}