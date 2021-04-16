package com.example.androidpractice.dagger

import android.app.Application
import com.example.androidpractice.MovieApplication
import com.example.androidpractice.dagger.model.ModelModule
import com.example.androidpractice.dagger.model.NetworkModule
import com.example.androidpractice.dagger.model.RoomModule
import com.example.androidpractice.dagger.presenter.MovieDetailsPresenterModule
import com.example.androidpractice.dagger.presenter.MovieListPresenterModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        FragmentBuildersModule::class,
        MovieListPresenterModule::class,
        MovieDetailsPresenterModule::class,
        ModelModule::class,
        NetworkModule::class,
        RoomModule::class]
)
interface AppComponent : AndroidInjector<MovieApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}