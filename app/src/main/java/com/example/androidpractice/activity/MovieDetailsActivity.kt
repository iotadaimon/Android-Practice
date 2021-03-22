package com.example.androidpractice.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.androidpractice.MovieDetailsView
import com.example.androidpractice.R
import com.example.androidpractice.model.entity.Movie

class MovieDetailsActivity : AppCompatActivity(), MovieDetailsView {

    companion object {
        const val DATA_MOVIE: String = "DATA_MOVIE"
    }

    private lateinit var movie: Movie

    private lateinit var linearLayout: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        movie = intent.getParcelableExtra(DATA_MOVIE) ?: Movie().also {
            Toast.makeText(
                this,
                resources.getText(R.string.movie_details_error_message),
                Toast.LENGTH_SHORT
            ).show()
        }

        supportActionBar?.title = movie.title

        linearLayout = findViewById(R.id.movie_details_linear_layout)

        // TODO - Add poster
        val posterImageView: ImageView = findViewById(R.id.movie_poster_image_view)
//         val moviePoster = movie.posterPath

        // Add properties
        for ((key, value) in movie.getMovieProperties()) {
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

    fun toggleFavouriteMovie(view: View) {
        // TODO - call toggleFavouriteMovie with movie data
    }

    override fun toggleFavouriteMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    // Extracts and returns a poster and a list of properties from a Movie instance
    private fun Movie.getMovieProperties(): Map<String, Any?> = linkedMapOf<String, Any?>(
        "Adult" to movie.adult,
        "Overview" to movie.overview,
        "Release Date" to movie.releaseDate,
        "ID" to movie.id,
        "Genre IDs" to movie.genreIDs,
        "Original Title" to movie.originalTitle,
        "Language" to movie.originalLanguage,
        "Title" to movie.title,
        "Backdrop Path" to movie.backdropPath,
        "Popularity" to movie.popularity,
        "Vote Vount" to movie.voteCount,
        "Video" to movie.video,
        "Vote Average" to movie.voteAverage
    )

}