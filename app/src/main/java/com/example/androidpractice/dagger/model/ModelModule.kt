package com.example.androidpractice.dagger.model

import com.example.androidpractice.MovieModel
import com.example.androidpractice.MutableMovieModel
import com.example.androidpractice.dagger.LocalImmutableModel
import com.example.androidpractice.dagger.WebModel
import com.example.androidpractice.model.local.LocalStorageModel
import com.example.androidpractice.model.web.TMDBModel
import dagger.Binds
import dagger.Module

@Module
interface ModelModule {

    @Binds
    @WebModel
    fun provideTMDBModel(tmdbModel: TMDBModel): MovieModel

    @Binds
    @LocalImmutableModel
    fun provideImmutableLocalModel(localStorageModel: LocalStorageModel): MovieModel

    @Binds
    fun provideMutableLocalModel(localStorageModel: LocalStorageModel): MutableMovieModel
}