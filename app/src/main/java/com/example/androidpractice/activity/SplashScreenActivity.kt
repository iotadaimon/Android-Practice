package com.example.androidpractice.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpractice.R
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SplashScreenActivity"
        private const val SPLASH_SCREEN_DELAY_MS = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN // Set to fullscreen

        // Launch with a delay
        Handler(Looper.getMainLooper()).postDelayed(this::launchApp, SPLASH_SCREEN_DELAY_MS)
    }

    private fun launchApp() {
        // Check if user is logged into Firebase
        when (FirebaseAuth.getInstance().currentUser) {
            null -> {
                Log.d(TAG, "Firebase user is null, starting the sign-in activity")
                launchGoogleSignInActivity()
            }
            else -> {
                Log.d(TAG, "Launching app with firebase user credentials")
                launchMainActivity()
            }
        }
    }

    private fun launchGoogleSignInActivity() {
        val intent = Intent(this, GoogleLoginActivity::class.java)
        startActivityForResult(intent, 0) // Dummy request code since we don't expect any data
    }

    private fun launchMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) launchMainActivity()
    }
}