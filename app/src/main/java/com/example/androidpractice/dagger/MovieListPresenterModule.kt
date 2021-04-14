package com.example.androidpractice.dagger

import com.example.androidpractice.MovieListContract
import com.example.androidpractice.MovieModel
import com.example.androidpractice.movielist.presenter.MovieListPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
object MovieListPresenterModule {

    @Singleton
    @Provides
    @Named("web_presenter")
    fun provideAllMoviesPresenter(
        movieListPresenter: MovieListPresenter,
        @Named("web_model") model: MovieModel
    ): MovieListContract.Presenter {
        movieListPresenter.model = model
        return movieListPresenter
    }

    @Singleton
    @Provides
    @Named("local_presenter")
    fun provideLikedMoviesPresenter(
        movieListPresenter: MovieListPresenter,
        @Named("local_immutable_model") model: MovieModel
    ): MovieListContract.Presenter {
        movieListPresenter.model = model
        return movieListPresenter
    }
}