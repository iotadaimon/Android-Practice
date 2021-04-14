package com.example.androidpractice.dagger

import com.example.androidpractice.movielist.view.AllMoviesFragment
import com.example.androidpractice.movielist.view.LikedMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuildersModule {

    @ContributesAndroidInjector
    fun contributeAllMoviesFragment(): AllMoviesFragment

    @ContributesAndroidInjector
    fun contributeLikedMoviesFragment(): LikedMoviesFragment
}