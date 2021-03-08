package com.example.androidpractice

import com.example.androidpractice.model.TMDBModel
import com.example.androidpractice.model.TMDBService
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TMDBModelTests {

    private companion object {
        const val TEST_BASE_URL = "https://api.themoviedb.org/3/movie/"
        const val TEST_API_KEY = "bd1a81fe362d2ec1258025a8ceb7a552"
    }

    private lateinit var tmdbService: TMDBService

    @Before
    fun setUp() {
        tmdbService = Retrofit.Builder()
            .baseUrl(TEST_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TMDBService::class.java)
    }

    @Test
    fun testServiceGetPopularMoviesWithApiKeyOnly() {
        val response = tmdbService.getPopularMovies(TEST_API_KEY).execute()

        if (response.isSuccessful) {
            Assert.assertNotNull(response.body())
        }
    }

    @Test
    fun testModelGetPopularMoviesWithApiKeyOnly() {
        val model = TMDBModel(tmdbService)
        assertTrue(model.getPopularMovies(TEST_API_KEY).isNotEmpty())
    }

}