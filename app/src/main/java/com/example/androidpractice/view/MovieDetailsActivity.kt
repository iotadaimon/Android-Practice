package com.example.androidpractice.view

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpractice.MovieDetailsPresenter
import com.example.androidpractice.MovieDetailsView
import com.example.androidpractice.R
import com.example.androidpractice.model.entity.Movie
import com.example.androidpractice.model.local.LocalStorageModel
import com.example.androidpractice.model.local.MovieDatabaseSingleton
import com.example.androidpractice.presenter.MovieDetailsPresenterImpl

class MovieDetailsActivity : AppCompatActivity(), MovieDetailsView {

    companion object {
        const val DATA_MOVIE: String = "DATA_MOVIE"
    }

    private lateinit var posterImageView: ImageView
    private lateinit var linearLayout: LinearLayout

    private lateinit var movie: Movie

    private lateinit var presenter: MovieDetailsPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        linearLayout = findViewById(R.id.movie_details_linear_layout)
        posterImageView = findViewById(R.id.movie_poster_image_view)

        movie = intent.getParcelableExtra(DATA_MOVIE) ?: Movie().also {
            Toast.makeText(
                this,
                resources.getText(R.string.movie_details_error_message),
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }

        presenter = MovieDetailsPresenterImpl(
            LocalStorageModel(MovieDatabaseSingleton.movieDAO),
            this
        )

        presenter.presentMovieDetails(movie)
    }

    fun toggleFavouriteMovie(view: View) = toggleFavouriteMovie(movie)

    override fun showMovieDetails(poster: Bitmap, properties: Map<String, Any?>) {
        supportActionBar?.title = movie.title

        // Add properties
        for ((key, value) in properties) {
            val moviePropertyView =
                layoutInflater.inflate(R.layout.item_movie_property, null)
            val propertyNameTextView: TextView =
                moviePropertyView.findViewById(R.id.movie_property_name_textView)
            val propertyValueTextView: TextView =
                moviePropertyView.findViewById(R.id.movie_property_value_textView)

            propertyNameTextView.text = key
            propertyValueTextView.text = value.toString()

            linearLayout.addView(moviePropertyView)
        }
    }

    // TODO - change floating button icon on toggle
    override fun showAddedMovieMessage() = Toast.makeText(
        this,
        "Deleted liked movie",
        Toast.LENGTH_SHORT
    ).show()

    override fun showDeletedMovieMessage() = Toast.makeText(
        this,
        "Added liked movie",
        Toast.LENGTH_SHORT
    ).show()

    override fun toggleFavouriteMovie(movie: Movie) = presenter.toggleFavouriteMovie(movie)

}