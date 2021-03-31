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
import androidx.fragment.app.commit
import com.example.androidpractice.R
import com.example.androidpractice.movielist.view.AllMoviesFragment
import com.example.androidpractice.movielist.view.LikedMoviesFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView
    private lateinit var fragmentFrameLayout: FrameLayout

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        navigationView = findViewById(R.id.navigation_view)

        firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser

        initDrawerHeaderWithUserData(firebaseUser)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_all_movies -> showAllMoviesFragment()
                R.id.nav_liked_movies -> showLikedMoviesFragment()
                R.id.nav_logout -> logoutAndFinish()
            }
            true
        }

        fragmentFrameLayout = findViewById(R.id.fragment_frameLayout)
    }

    private fun initDrawerHeaderWithUserData(firebaseUser: FirebaseUser?) {
        // Get layout elements
        val navigationHeader = navigationView.getHeaderView(0)
        val drawerHeaderImageView: ImageView =
            navigationHeader.findViewById(R.id.drawer_header_imageView)
        val drawerHeaderNameTextView: TextView =
            navigationHeader.findViewById(R.id.drawer_header_name_textView)
        val drawerHeaderEmailTextView: TextView =
            navigationHeader.findViewById(R.id.drawer_header_email_textView)

        // Load user photo
        val photoUrl = firebaseUser?.photoUrl
        if (photoUrl != null) Picasso.get().load(photoUrl).into(drawerHeaderImageView)

        // Assign text with name and email to header fields
        drawerHeaderNameTextView.text = firebaseUser?.displayName
        drawerHeaderEmailTextView.text = firebaseUser?.email
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()

        if (savedInstanceState == null) {
            // Show the default fragment on app launch
            navigationView.setCheckedItem(R.id.nav_all_movies)
            showAllMoviesFragment()
        }
    }

    private fun showAllMoviesFragment() =
        switchFragment(getString(R.string.nav_all_movies), AllMoviesFragment())

    private fun showLikedMoviesFragment() =
        switchFragment(getString(R.string.nav_liked_movies), LikedMoviesFragment())

    /**
     *  Show the fragment passed through the [fragment] parameter and change the title
     *  with the value passed into [toolbarTitle].
     */
    private fun switchFragment(toolbarTitle: String, fragment: androidx.fragment.app.Fragment) {
        toolbar.title = toolbarTitle

        supportFragmentManager.commit {
            replace(R.id.fragment_frameLayout, fragment)
        }

        drawerLayout.closeDrawers()
    }

    /**
     * Log out the user from Firebase and close the app.
     */
    private fun logoutAndFinish() {
        firebaseAuth.signOut()
        Toast.makeText(this, "Restart the app to log in with a different user", Toast.LENGTH_SHORT)
            .show()
        finish()
    }

}