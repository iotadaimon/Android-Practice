package com.example.androidpractice.view

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.androidpractice.MovieDetailsPresenter
import com.example.androidpractice.MovieDetailsView
import com.example.androidpractice.R
import com.example.androidpractice.model.entity.Movie
import com.example.androidpractice.model.local.LocalStorageModel
import com.example.androidpractice.model.local.MovieDatabaseSingleton
import com.example.androidpractice.presenter.MovieDetailsPresenterImpl
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MovieDetailsActivity : AppCompatActivity(), MovieDetailsView {

    companion object {
        const val DATA_MOVIE: String = "DATA_MOVIE"
    }

    private lateinit var posterImageView: ImageView
    private lateinit var linearLayout: LinearLayout
    private lateinit var floatingActionButton: FloatingActionButton

    private lateinit var movie: Movie

    private lateinit var presenter: MovieDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        linearLayout = findViewById(R.id.movie_details_linear_layout)
        posterImageView = findViewById(R.id.movie_poster_image_view)
        floatingActionButton = findViewById(R.id.fab)

        movie = intent.getParcelableExtra(DATA_MOVIE) ?: Movie().also {
            Toast.makeText(
                this,
                resources.getText(R.string.movie_details_error_message),
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }

        presenter = MovieDetailsPresenterImpl(view = this)
    }

    override fun onStart() {
        super.onStart()
        presenter.presentMovieDetails(movie)
        updateFabIcon()
    }

    fun toggleFavouriteMovie(view: View) = toggleLikedMovie(movie)

    override fun showMovieDetails(poster: Bitmap, properties: Map<String, Any?>) {
        supportActionBar?.title = movie.title

        // Add properties
        for ((key, value) in properties) {
            val moviePropertyView =
                layoutInflater.inflate(R.layout.recyclerview_item_movie_property, null)
            val propertyNameTextView: TextView =
                moviePropertyView.findViewById(R.id.movie_property_name_textView)
            val propertyValueTextView: TextView =
                moviePropertyView.findViewById(R.id.movie_property_value_textView)

            propertyNameTextView.text = key
            propertyValueTextView.text = value.toString()

            linearLayout.addView(moviePropertyView)
        }
    }

    override fun showAddedMovieMessage() {
        makeToast(resources.getString(R.string.liked_movie_added)).show()
        setFabDrawable(R.drawable.ic_baseline_liked_24)
    }

    override fun showDeletedMovieMessage() {
        makeToast(resources.getString(R.string.liked_movie_removed)).show()
        setFabDrawable(R.drawable.ic_baseline_liked_border_24)
    }

    override fun toggleLikedMovie(movie: Movie) = presenter.toggleLikedMovie(movie)

    private fun updateFabIcon() {
        val drawableResId: Int = when (presenter.checkIfLiked(movie)) {
            true -> R.drawable.ic_baseline_liked_24
            false -> R.drawable.ic_baseline_liked_border_24
        }

        setFabDrawable(drawableResId)
    }

    private fun setFabDrawable(drawableResId: Int) = floatingActionButton.setImageDrawable(
        ContextCompat.getDrawable(
            this,
            drawableResId
        )
    )


    private fun makeToast(message: String) = Toast.makeText(
        this,
        message,
        Toast.LENGTH_SHORT
    )

}