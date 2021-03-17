package com.example.androidpractice.activity

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.androidpractice.R
import com.example.androidpractice.view.AllMoviesFragment
import com.example.androidpractice.view.LikedMoviesFragment


class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView
    private lateinit var fragmentFrameLayout: FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
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

        navigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_all_movies -> showAllMoviesFragment()
                R.id.nav_liked_movies -> showLikedMoviesFragment()
                R.id.nav_logout -> Toast
                    .makeText(this, "Not implemented", Toast.LENGTH_SHORT)
                    .show()
            }
            true
        }

        fragmentFrameLayout = findViewById(R.id.fragment_frameLayout)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()

        if (savedInstanceState == null) {
            // Show the default fragment
            navigationView.setCheckedItem(R.id.nav_all_movies)
            showAllMoviesFragment()
        }
    }

    private fun showAllMoviesFragment() =
        switchFragment(getString(R.string.nav_all_movies), AllMoviesFragment())

    private fun showLikedMoviesFragment() =
        switchFragment(getString(R.string.nav_liked_movies), LikedMoviesFragment())

    private fun switchFragment(toolbarTitle: String, fragment: androidx.fragment.app.Fragment) {
        toolbar.setTitle(toolbarTitle)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.fragment_frameLayout,
            fragment
        )
        fragmentTransaction.commit()

        drawerLayout.closeDrawers()
    }
}