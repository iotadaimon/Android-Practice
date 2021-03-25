package com.example.androidpractice.activity

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.androidpractice.R
import com.example.androidpractice.view.AllMoviesFragment
import com.example.androidpractice.view.LikedMoviesFragment
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

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

        val navigationHeader = navigationView.getHeaderView(0)
        val drawerHeaderImageView: ImageView =
            navigationHeader.findViewById(R.id.drawer_header_imageView)
        val drawerHeaderNameTextView: TextView =
            navigationHeader.findViewById(R.id.drawer_header_name_textView)
        val drawerHeaderEmailTextView: TextView =
            navigationHeader.findViewById(R.id.drawer_header_email_textView)

        val firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

        // Load user photo
        val photoUrl = firebaseUser?.photoUrl
        if (photoUrl != null) Picasso.get().load(photoUrl).into(drawerHeaderImageView)

        drawerHeaderNameTextView.text = firebaseUser?.displayName
        drawerHeaderEmailTextView.text = firebaseUser?.email

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_all_movies -> showAllMoviesFragment()
                R.id.nav_liked_movies -> showLikedMoviesFragment()
                R.id.nav_logout -> logout()
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

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        finish()
    }

    private fun switchFragment(toolbarTitle: String, fragment: androidx.fragment.app.Fragment) {
        toolbar.title = toolbarTitle

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.fragment_frameLayout,
            fragment
        )
        fragmentTransaction.commit()

        drawerLayout.closeDrawers()
    }

}