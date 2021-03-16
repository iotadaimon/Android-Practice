package com.example.androidpractice.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice.R
import com.example.androidpractice.model.entity.Movie

class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        const val DATA_MOVIE: String = "DATA_MOVIE"
    }

    private lateinit var movie: Movie

    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        recyclerView = findViewById(R.id.movie_details_recycler_view)

        movie = intent.getParcelableExtra(DATA_MOVIE) ?: Movie().also {
            Toast.makeText(
                this,
                resources.getText(R.string.movie_details_error_message),
                Toast.LENGTH_SHORT
            ).show()
        }

//        recyclerView.adapter =  // TODO
    }

}