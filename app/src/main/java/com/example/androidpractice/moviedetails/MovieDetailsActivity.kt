package com.example.androidpractice.moviedetails

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.androidpractice.MovieDetailsContract
import com.example.androidpractice.R
import com.example.androidpractice.model.entity.Movie
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MovieDetailsActivity : AppCompatActivity(),
    MovieDetailsContract.View {

    companion object {
        const val DATA_MOVIE: String = "DATA_MOVIE"
    }

    private lateinit var posterImageView: ImageView
    private lateinit var linearLayout: LinearLayout
    private lateinit var floatingActionButton: FloatingActionButton

    private lateinit var movie: Movie

    private lateinit var presenter: MovieDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        linearLayout = findViewById(R.id.movie_details_linear_layout)
        posterImageView = findViewById(R.id.movie_poster_image_view)
        floatingActionButton = findViewById(R.id.fab)

        movie = intent.getParcelableExtra(DATA_MOVIE) ?: Movie().also {
            makeToast(resources.getString(R.string.movie_details_error_message))
            finish()
        }

        presenter = MovieDetailsPresenter()
        presenter.attachView(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.presentMovieDetails(movie)
        presenter.presentLikedStatus(movie)
    }

    override fun showMovieDetails(posterBitmap: Bitmap?, properties: Map<String, Any?>) {
        supportActionBar?.title = movie.title

        posterBitmap?.let { posterImageView.setImageBitmap(it) }

        // Show movie properties
        properties.entries.forEach { mapEntry ->
            val moviePropertyView = getInflatedMoviePropertyView(mapEntry.key, mapEntry.value)
            linearLayout.addView(moviePropertyView)
        }
    }

    private fun getInflatedMoviePropertyView(propertyName: String, propertyValue: Any?): View {
        val moviePropertyView =
            layoutInflater.inflate(R.layout.recyclerview_item_movie_property, null)
        val propertyNameTextView: TextView =
            moviePropertyView.findViewById(R.id.movie_property_name_textView)
        val propertyValueTextView: TextView =
            moviePropertyView.findViewById(R.id.movie_property_value_textView)

        propertyNameTextView.text = propertyName
        propertyValueTextView.text = propertyValue.toString()

        return moviePropertyView
    }

    override fun showLikedStatus(isLiked: Boolean) = updateFabIcon(isLiked)

    private fun updateFabIcon(isLiked: Boolean) {
        val drawableResId: Int = when (isLiked) {
            true -> R.drawable.ic_baseline_liked_24
            false -> R.drawable.ic_baseline_liked_border_24
        }

        val drawable = ContextCompat.getDrawable(this, drawableResId)
        floatingActionButton.setImageDrawable(drawable)
    }

    fun toggleLikedMovie(view: View) = toggleLikedMovie(movie)

    override fun toggleLikedMovie(movie: Movie) = presenter.toggleLikedMovie(movie)

    override fun showAddedMovieMessage() =
        makeToast(resources.getString(R.string.liked_movie_added)).show()

    override fun showDeletedMovieMessage() =
        makeToast(resources.getString(R.string.liked_movie_removed)).show()

    private fun makeToast(message: String) = Toast.makeText(
        this,
        message,
        Toast.LENGTH_SHORT
    )
}