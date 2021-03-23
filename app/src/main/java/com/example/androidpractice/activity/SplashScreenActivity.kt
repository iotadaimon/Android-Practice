package com.example.androidpractice.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpractice.R
import com.example.androidpractice.model.local.MovieDatabaseSingleton

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN    // Set to fullscreen

        // Wait for something to be done and launch MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            MovieDatabaseSingleton.prepareDatabase(this)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)
    }
}