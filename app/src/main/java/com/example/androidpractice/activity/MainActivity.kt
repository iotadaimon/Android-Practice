package com.example.androidpractice.activity

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.androidpractice.Contract
import com.example.androidpractice.Presenter
import com.example.androidpractice.R
import com.example.androidpractice.model.LocalStorageModel
import com.example.androidpractice.model.TMDBModel
import com.example.androidpractice.model.TMDBService
import com.example.androidpractice.model.entity.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), Contract.View {

    private lateinit var presenter: Contract.Presenter

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.navigation_view)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_all_movies, R.id.nav_liked_movies),
            drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)

        initialisePresenter()
    }

    private fun initialisePresenter() {
        val tmdbService = Retrofit.Builder()
            .baseUrl(TMDBService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TMDBService::class.java)

        presenter = Presenter(TMDBModel(tmdbService), LocalStorageModel(), this)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun showAllMovies(movies: List<Movie>) {
        TODO("Not yet implemented")
    }

    override fun showLikedMovies(movies: List<Movie>) {
        TODO("Not yet implemented")
    }

}