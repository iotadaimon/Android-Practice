package com.example.androidpractice.dagger

import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @Provides
    fun providePicasso(): Picasso = Picasso.get()

    @Provides
    fun providePageSize(): Int = 20
}