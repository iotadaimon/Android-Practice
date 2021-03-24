package com.example.androidpractice.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.androidpractice.model.entity.Movie

@Dao
interface MovieDAO {

    @Query("SELECT * FROM liked_movies")
    fun getAll(): List<Movie>

    @Query("SELECT * FROM liked_movies ORDER BY title LIMIT (:rowNumber) OFFSET (:offset)")
    fun loadAllBetweenRows(offset: Long, rowNumber:Int): List<Movie>

    @Query("SELECT * FROM liked_movies WHERE id = (:movieId)")
    fun loadAllById(movieId: Int): List<Movie>

    @Query("SELECT * FROM liked_movies WHERE id IN (:movieIds)")
    fun loadAllByIds(movieIds: IntArray): List<Movie>

    @Insert
    fun insertAll(vararg movies: Movie)

    @Delete
    fun delete(movie: Movie)

}