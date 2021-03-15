package com.example.androidpractice.activity

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
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

// TODO - Load all movies at launch

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var frameLayout: FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                R.id.nav_all_movies -> switchFragment(AllMoviesFragment())
                R.id.nav_liked_movies -> switchFragment(LikedMoviesFragment())
                R.id.nav_logout -> Toast
                    .makeText(this, "Not implemented", Toast.LENGTH_SHORT)
                    .show()
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

    private fun switchFragment(fragment: androidx.fragment.app.Fragment) {
        drawerLayout.closeDrawers()

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.fragment_frameLayout,
            fragment
        )
        fragmentTransaction.commit()
    }
}