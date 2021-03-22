package com.example.androidpractice.model.local

import android.content.Context
import androidx.room.Room

object MovieDatabaseSingleton {

    internal lateinit var movieDatabase: MovieDatabase
        private set

    internal lateinit var movieDAO: MovieDAO
        private set

    // Call in advance
    fun prepareDatabase(context: Context) {
        movieDatabase = Room.databaseBuilder(
            context,
            MovieDatabase::class.java, "database-name"
        ).build()

        movieDAO = movieDatabase.movieDao()
    }

}