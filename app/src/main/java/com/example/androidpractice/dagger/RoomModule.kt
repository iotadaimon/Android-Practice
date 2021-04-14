package com.example.androidpractice.dagger

import android.app.Application
import androidx.room.Room
import com.example.androidpractice.model.local.MovieDAO
import com.example.androidpractice.model.local.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(application: Application): MovieDatabase = Room.databaseBuilder(
        application,
        MovieDatabase::class.java, "movie_database"
    ).build()

    @Singleton
    @Provides
    fun provideLocalDao(movieDatabase: MovieDatabase): MovieDAO = movieDatabase.movieDao()
}