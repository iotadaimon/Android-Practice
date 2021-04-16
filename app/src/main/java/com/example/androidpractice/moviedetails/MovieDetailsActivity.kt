package com.example.androidpractice.moviedetails

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.androidpractice.MovieDetailsContract
import com.example.androidpractice.R
import com.example.androidpractice.databinding.ActivityMovieDetailsBinding
import com.example.androidpractice.databinding.RecyclerviewItemMoviePropertyBinding
import com.example.androidpractice.model.entity.Movie
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MovieDetailsActivity : DaggerAppCompatActivity(),
    MovieDetailsContract.View {

    companion object {
        const val DATA_MOVIE: String = "DATA_MOVIE"
    }

    private lateinit var binding: ActivityMovieDetailsBinding

    private lateinit var movie: Movie

    @Inject
    lateinit var presenter: MovieDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movie = intent.getParcelableExtra(DATA_MOVIE) ?: Movie().also {
            showErrorToast()
            finish()
        }

        presenter.attachView(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.presentMovieDetails(movie)
        presenter.presentLikedStatus(movie)
    }

    override fun showMovieDetails(posterBitmap: Bitmap?, properties: Map<String, Any?>) {
        supportActionBar?.title = movie.title

        posterBitmap?.let { binding.moviePosterImageView.setImageBitmap(it) }

        // Show movie properties
        properties.entries.forEach { mapEntry ->
            val moviePropertyView = getInflatedMoviePropertyView(mapEntry.key, mapEntry.value)
            binding.movieDetailsLinearLayout.addView(moviePropertyView)
        }
    }

    private fun getInflatedMoviePropertyView(propertyName: String, propertyValue: Any?): View {
        val moviePropertyBinding = RecyclerviewItemMoviePropertyBinding.inflate(layoutInflater)

        moviePropertyBinding.moviePropertyNameTextView.text = propertyName
        moviePropertyBinding.moviePropertyValueTextView.text = propertyValue.toString()

        return moviePropertyBinding.root
    }

    override fun showLikedStatus(isLiked: Boolean) = updateFabIcon(isLiked)

    private fun updateFabIcon(isLiked: Boolean) {
        val drawableResId: Int = when (isLiked) {
            true -> R.drawable.ic_baseline_liked_24
            false -> R.drawable.ic_baseline_liked_border_24
        }

        val drawable = ContextCompat.getDrawable(this, drawableResId)
        binding.fab.setImageDrawable(drawable)
    }

    fun toggleLikedMovie(view: View) = toggleLikedMovie(movie)

    override fun toggleLikedMovie(movie: Movie) = presenter.toggleLikedMovie(movie)

    override fun showAddedMovieMessage() =
        makeToast(resources.getString(R.string.liked_movie_added)).show()

    override fun showDeletedMovieMessage() =
        makeToast(resources.getString(R.string.liked_movie_removed)).show()

    override fun showErrorToast() =
        makeToast(resources.getString(R.string.movie_details_error_message)).show()

    private fun makeToast(message: String) = Toast.makeText(
        this,
        message,
        Toast.LENGTH_SHORT
    )
}