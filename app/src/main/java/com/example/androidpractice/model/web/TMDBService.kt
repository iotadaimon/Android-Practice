package com.example.androidpractice.model.web

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {

    companion object {
        const val BASE_URL: String = "https://api.themoviedb.org/3/movie/"
        const val DEFAULT_LANGUAGE_ARGUMENT: String = "en-US"
        const val DEFAULT_PAGE_ARGUMENT: Int = 1
        const val DEFAULT_REGION_ARGUMENT: String = ""
    }

    @GET("popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = DEFAULT_LANGUAGE_ARGUMENT,
        @Query("page") page: Int = DEFAULT_PAGE_ARGUMENT,
        @Query("region") region: String = DEFAULT_REGION_ARGUMENT
    ): Single<TMDBResponse>

}