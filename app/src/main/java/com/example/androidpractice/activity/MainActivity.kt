package com.example.androidpractice.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
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

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPresenter()
        initDrawerMenu()
    }

    private fun initPresenter() {
        val tmdbService = Retrofit.Builder()
            .baseUrl(TMDBService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TMDBService::class.java)

        presenter = Presenter(TMDBModel(tmdbService), LocalStorageModel(), this)
    }

    private fun initDrawerMenu() {
        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item))
            true
        else
            super.onOptionsItemSelected(item)
    }

    override fun showAllMovies(movies: List<Movie>) {
        TODO("Not yet implemented")
    }

    override fun showLikedMovies(movies: List<Movie>) {
        TODO("Not yet implemented")
    }
}