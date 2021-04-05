package com.example.androidpractice.movielist.view.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice.MovieListContract
import com.example.androidpractice.R
import com.example.androidpractice.model.entity.Movie

class MovieAdapter(
    internal var movieList: List<Movie>,
    private val movieListView: MovieListContract.View
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val movieTitleTextView: TextView =
            itemView.findViewById(R.id.movie_item_title_textView)
        private val movieVoteAverageTextView: TextView =
            itemView.findViewById(R.id.movie_item_vote_average_textView)

        fun bind(movie: Movie) {
            movieTitleTextView.text = movie.title
            movieVoteAverageTextView.text = movie.voteAverage.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_movie, parent, false)

        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
        holder.itemView.setOnClickListener {
            movieListView.showMovieDetails(movieList[position]) // Open movie details on click on the movie list item
        }
    }
}
