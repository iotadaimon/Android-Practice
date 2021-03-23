package com.example.androidpractice.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidpractice.model.entity.Movie

@Database(entities = arrayOf(Movie::class), version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDAO

}
