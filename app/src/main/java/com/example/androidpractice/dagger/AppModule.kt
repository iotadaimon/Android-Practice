package com.example.androidpractice.dagger

import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    fun providePicasso(): Picasso = Picasso.get()

    @Provides
    fun providePageSize(): Int = 20
}