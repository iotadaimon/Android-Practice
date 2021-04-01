package com.example.androidpractice.model.local

import android.content.Context
import androidx.room.Room

object MovieDatabaseSingleton {

    internal lateinit var movieDatabase: MovieDatabase
        private set

    internal lateinit var movieDAO: MovieDAO
        private set

    /**
     * Initialize class members with appropriate values using the given [Context].
     * Warning - this method should be called before interacting with the class members.
     */
    fun prepareDatabase(context: Context) {
        movieDatabase = Room.databaseBuilder(
            context,
            MovieDatabase::class.java, "database-name"
        ).build()

        movieDAO = movieDatabase.movieDao()
    }
}