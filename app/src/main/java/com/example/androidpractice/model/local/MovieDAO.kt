package com.example.androidpractice.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.androidpractice.model.entity.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MovieDAO {

    @Query("SELECT * FROM liked_movies ORDER BY title LIMIT (:rowNumber) OFFSET (:offset)")
    fun loadRowsWithOffset(offset: Long, rowNumber: Int): Single<List<Movie>>

    @Query("SELECT * FROM liked_movies WHERE id = (:movieId)")
    fun loadAllById(movieId: Int): Single<List<Movie>>

    @Insert
    fun insertAll(vararg movies: Movie): Completable

    @Delete
    fun delete(movie: Movie): Completable
}