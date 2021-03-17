package com.example.androidpractice.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice.R
import com.example.androidpractice.model.entity.Movie
import com.example.androidpractice.view.MovieDetailsAdapter

class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        const val DATA_MOVIE: String = "DATA_MOVIE"
    }

    private lateinit var movie: Movie

    private lateinit var recyclerView: RecyclerView


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

        recyclerView = findViewById(R.id.movie_details_recycler_view)

        val movieProperties = movie.getMovieProperties()

        recyclerView.adapter = MovieDetailsAdapter(movieProperties)
        supportActionBar?.setTitle(movie.title)
    }

    // Extracts and returns a poster and a list of properties from a Movie instance
    private fun Movie.getMovieProperties(): List<Pair<String, Any?>> = listOf<Pair<String, Any?>>(
        Pair("Adult", movie.adult),
        Pair("Overview", movie.overview),
        Pair("Release Date", movie.releaseDate),
//            Pair("ID", movie.id),
//            Pair("Genre IDs", movie.genreIDs),
        Pair("Original Title", movie.originalTitle),
        Pair("Language", movie.originalLanguage),
        Pair("Title", movie.title),
//            Pair("Backdrop Path", movie.backdropPath),
        Pair("Popularity", movie.popularity),
        Pair("Vote Vount", movie.voteCount),
//            Pair("Video", movie.video),
        Pair("Vote Average", movie.voteAverage)
    )


}