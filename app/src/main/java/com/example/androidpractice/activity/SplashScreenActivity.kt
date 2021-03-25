package com.example.androidpractice.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpractice.R
import com.example.androidpractice.model.local.MovieDatabaseSingleton
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SplashScreenActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN    // Set to fullscreen

        MovieDatabaseSingleton.prepareDatabase(this)

        Handler(Looper.getMainLooper()).postDelayed({
            if (FirebaseAuth.getInstance().currentUser == null) {
                Log.d(TAG, "Firebase user is null, starting the sign-in activity")
                val intent = Intent(this, GoogleLoginActivity::class.java)
                startActivityForResult(intent, 0)
            } else {
                Log.d(TAG, "Launching app with firebase user credentials")
                launchApp()
            }
        }, 2000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            launchApp()
        }
    }

    private fun launchApp() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}