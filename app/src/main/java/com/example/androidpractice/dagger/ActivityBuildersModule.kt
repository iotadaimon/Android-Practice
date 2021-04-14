package com.example.androidpractice.dagger

import com.example.androidpractice.activity.MainActivity
import com.example.androidpractice.moviedetails.MovieDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuildersModule {

    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun contributeMovieDetailsActivity(): MovieDetailsActivity
}