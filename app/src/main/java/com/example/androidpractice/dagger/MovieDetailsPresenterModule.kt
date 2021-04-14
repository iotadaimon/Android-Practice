package com.example.androidpractice.dagger

import com.example.androidpractice.MovieDetailsContract
import com.example.androidpractice.MutableMovieModel
import com.example.androidpractice.moviedetails.MovieDetailsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
object MovieDetailsPresenterModule {

    @Singleton
    @Provides
    fun provideMovieDetailsPresenter(
        movieDetailsPresenter: MovieDetailsPresenter,
        @Named("local_mutable_model") model: MutableMovieModel
    ): MovieDetailsContract.Presenter {
        movieDetailsPresenter.model = model
        return movieDetailsPresenter
    }
}