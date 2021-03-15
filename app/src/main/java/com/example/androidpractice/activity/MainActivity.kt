package com.example.androidpractice.activity

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.androidpractice.Contract
import com.example.androidpractice.Presenter
import com.example.androidpractice.R
import com.example.androidpractice.fragment.AllMoviesFragment
import com.example.androidpractice.fragment.LikedMoviesFragment
import com.example.androidpractice.model.LocalStorageModel
import com.example.androidpractice.model.TMDBModel
import com.example.androidpractice.model.TMDBService
import com.example.androidpractice.model.entity.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO - Load all movies at launch

class MainActivity : AppCompatActivity(), Contract.View {

    companion object {
        const val BUNDLE_DATA_KEY = "DATA_MOVIE_LIST"
    }

    private lateinit var presenter: Contract.Presenter

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var frameLayout: FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialisePresenter()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_all_movies -> presenter.presentAllMovies()
                R.id.nav_liked_movies -> presenter.presentLikedMovies()
            }
            true
        }

        frameLayout = findViewById(R.id.fragment_frameLayout)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initialisePresenter() {
        val tmdbService = Retrofit.Builder()
            .baseUrl(TMDBService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TMDBService::class.java)

        presenter = Presenter(TMDBModel(tmdbService), LocalStorageModel(), this)
    }

    override fun showAllMovies(movies: List<Movie>) {
        showMoviesInFragment(movies, AllMoviesFragment::class.java)
    }

    override fun showLikedMovies(movies: List<Movie>) {
        showMoviesInFragment(movies, LikedMoviesFragment::class.java)
    }


    /**
     * Fills the [FrameLayout] with the fragment, whose class is specified by the [fragmentClass] argument,
     * passes a list of [Movie] instances for display in the fragment.
     */

    private fun showMoviesInFragment(
        movies: List<Movie>,
        fragmentClass: Class<out androidx.fragment.app.Fragment>
    ) {
        drawerLayout.closeDrawers()

        val arguments = Bundle().apply {
            putParcelableArrayList(
                BUNDLE_DATA_KEY,
                ArrayList(movies)
            )
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.fragment_frameLayout,
            fragmentClass,
            arguments
        )
        fragmentTransaction.commit()
    }

}