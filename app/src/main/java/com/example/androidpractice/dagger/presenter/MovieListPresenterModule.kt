package com.example.androidpractice.dagger.presenter

import com.example.androidpractice.MovieListContract
import com.example.androidpractice.MovieModel
import com.example.androidpractice.dagger.LocalImmutableModel
import com.example.androidpractice.dagger.LocalMoviePresenter
import com.example.androidpractice.dagger.WebModel
import com.example.androidpractice.dagger.WebMoviePresenter
import com.example.androidpractice.movielist.presenter.MovieListPresenter
import dagger.Module
import dagger.Provides

@Module
object MovieListPresenterModule {

    @Provides
    @WebMoviePresenter
    fun provideAllMoviesPresenter(@WebModel model: MovieModel): MovieListContract.Presenter =
        MovieListPresenter(model)

    @Provides
    @LocalMoviePresenter
    fun provideLikedMoviesPresenter(@LocalImmutableModel model: MovieModel): MovieListContract.Presenter =
        MovieListPresenter(model)

}