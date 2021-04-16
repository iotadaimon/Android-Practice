package com.example.androidpractice.dagger.presenter

import com.example.androidpractice.MovieDetailsContract
import com.example.androidpractice.moviedetails.MovieDetailsPresenter
import dagger.Binds
import dagger.Module

@Module
interface MovieDetailsPresenterModule {

    @Binds
    fun provideMovieDetailsPresenter(movieDetailsPresenter: MovieDetailsPresenter): MovieDetailsContract.Presenter
}