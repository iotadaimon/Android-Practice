package com.example.androidpractice.dagger

import com.example.androidpractice.MovieModel
import com.example.androidpractice.MutableMovieModel
import com.example.androidpractice.model.local.LocalStorageModel
import com.example.androidpractice.model.web.TMDBModel
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
interface ModelModule {

    @Binds
    @Named("web_model")
    fun provideTMDBModel(tmdbModel: TMDBModel): MovieModel

    @Binds
    @Named("local_immutable_model")
    fun provideImmutableLocalModel(localStorageModel: LocalStorageModel): MovieModel

    @Binds
    @Named("local_mutable_model")
    fun provideMutableLocalModel(localStorageModel: LocalStorageModel): MutableMovieModel
}