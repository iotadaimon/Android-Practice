package com.example.androidpractice.dagger.model

import android.app.Application
import androidx.room.Room
import com.example.androidpractice.dagger.DatabaseName
import com.example.androidpractice.model.local.MovieDAO
import com.example.androidpractice.model.local.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RoomModule {

    @Singleton
    @Provides
    @DatabaseName
    fun provideDatabaseName(): String = "movie_database"

    @Singleton
    @Provides
    fun provideMovieDatabase(
        application: Application,
        @DatabaseName databaseName: String
    ): MovieDatabase =
        Room.databaseBuilder(application, MovieDatabase::class.java, databaseName).build()

    @Singleton
    @Provides
    fun provideLocalDao(movieDatabase: MovieDatabase): MovieDAO = movieDatabase.movieDao()
}