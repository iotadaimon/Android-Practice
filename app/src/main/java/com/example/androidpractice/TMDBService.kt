package com.example.androidpractice

import com.example.androidpractice.model.entity.TMDBResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// TODO - Declare overloaded methods for Java interoperability
interface TMDBService {

    @GET("popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): Call<TMDBResponse>

}