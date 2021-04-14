package com.example.androidpractice.dagger

import com.example.androidpractice.model.web.TMDBService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @PosterRepositoryBaseUrl
    fun providePosterRepoBaseUrl(): String = "https://image.tmdb.org/t/p/w500"

    @Provides
    @ApiKey
    fun provideTMDBApiKey(): String = "bd1a81fe362d2ec1258025a8ceb7a552"

    @Singleton
    @Provides
    fun provideTMDBService(): TMDBService = Retrofit.Builder()
        .baseUrl(TMDBService.BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(TMDBService::class.java)
}