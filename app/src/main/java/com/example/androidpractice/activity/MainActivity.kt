package com.example.androidpractice.activity

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.androidpractice.Contract
import com.example.androidpractice.Presenter
import com.example.androidpractice.R
import com.example.androidpractice.fragment.AllMoviesFragment
import com.example.androidpractice.model.LocalStorageModel
import com.example.androidpractice.model.TMDBModel
import com.example.androidpractice.model.TMDBService
import com.example.androidpractice.model.entity.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO - Load all movies at launch

class MainActivity : AppCompatActivity(), Contract.View {

    private lateinit var presenter: Contract.Presenter

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialisePresenter()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.navigation_view)
        navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_all_movies, R.id.nav_liked_movies),
            drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_all_movies -> presenter.presentAllMovies()
                R.id.nav_liked_movies -> presenter.presentLikedMovies()
            }
            true
        }
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
        drawerLayout.closeDrawers()

        val arguments = Bundle()
        arguments.putParcelableArrayList("data", ArrayList(movies)) // TODO - define the key as a constant

        navController.navigate(R.id.nav_all_movies, arguments)
    }

    override fun showLikedMovies(movies: List<Movie>) {
        drawerLayout.closeDrawers()

        val arguments = Bundle()
        arguments.putParcelableArrayList("data", ArrayList(movies)) // TODO - define the key as a constant

        navController.navigate(R.id.nav_liked_movies, arguments)
    }

}