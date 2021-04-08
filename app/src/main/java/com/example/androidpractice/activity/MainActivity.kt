package com.example.androidpractice.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.androidpractice.R
import com.example.androidpractice.databinding.ActivityMainBinding
import com.example.androidpractice.databinding.DrawerHeaderMainBinding
import com.example.androidpractice.movielist.view.AllMoviesFragment
import com.example.androidpractice.movielist.view.LikedMoviesFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser

        initDrawerHeaderWithUserData(binding.navigationView.getHeaderView(0), firebaseUser)

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_all_movies -> showAllMoviesFragment()
                R.id.nav_liked_movies -> showLikedMoviesFragment()
                R.id.nav_logout -> logoutAndFinish()
            }
            true
        }
    }

    private fun initDrawerHeaderWithUserData(drawerHeaderView: View, firebaseUser: FirebaseUser?) {
        // Get layout elements
        val navigationHeader = DrawerHeaderMainBinding.bind(drawerHeaderView)
        val drawerHeaderImageView: ImageView = navigationHeader.drawerHeaderImageView
        val drawerHeaderNameTextView: TextView = navigationHeader.drawerHeaderNameTextView
        val drawerHeaderEmailTextView: TextView = navigationHeader.drawerHeaderEmailTextView

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
            binding.navigationView.setCheckedItem(R.id.nav_all_movies)
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
        binding.toolbar.title = toolbarTitle

        supportFragmentManager.commit {
            replace(R.id.fragment_frameLayout, fragment)
        }

        binding.drawerLayout.closeDrawers()
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