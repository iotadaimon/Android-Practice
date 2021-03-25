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
import com.google.firebase.auth.FirebaseUser

class SplashScreenActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SplashScreenActivity"
        const val RC_FIREBASE_ACCOUNT_DATA = 1111111
        const val FIREBASE_ACCOUNT_DATA = "firebase_account_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN    // Set to fullscreen

        MovieDatabaseSingleton.prepareDatabase(this)

        Handler(Looper.getMainLooper()).postDelayed({
            val firebaseUser = FirebaseAuth.getInstance().currentUser

            if (firebaseUser == null) {
                Log.d(TAG, "Firebase user is null, starting the sign-in activity")
                val intent = Intent(this, GoogleLoginActivity::class.java)
                startActivityForResult(intent, RC_FIREBASE_ACCOUNT_DATA)
            } else {
                Log.d(TAG, "Launching app with firebase user credentials")
                launchApp(firebaseUser)
            }
        }, 2000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_FIREBASE_ACCOUNT_DATA && resultCode == RESULT_OK) {
            val firebaseUser = intent.getParcelableExtra<FirebaseUser>(FIREBASE_ACCOUNT_DATA)!!
            launchApp(firebaseUser)
        }
    }

    private fun launchApp(firebaseUser: FirebaseUser) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(FIREBASE_ACCOUNT_DATA, firebaseUser)
        startActivity(intent)
        finish()
    }


}