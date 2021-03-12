package com.example.androidpractice.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice.R
import com.example.androidpractice.model.entity.Movie

class MovieAdapter(private val movieList: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    // Describes an item view and its place within the RecyclerView
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val moviePosterIconImageView: ImageView = itemView.findViewById(R.id.movie_item_poster_icon)
        private val movieTitleTextView: TextView = itemView.findViewById(R.id.movie_item_title_textView)
        private val movieVoteAverageTextView: TextView = itemView.findViewById(R.id.movie_item_vote_average_textView)

        fun bind(movie: Movie) {
            moviePosterIconImageView // TODO - Get picture and assign
            movieTitleTextView.text = movie.title
            movieVoteAverageTextView.text = movie.voteAverage.toString()
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)

        return MovieViewHolder(view)
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return movieList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

}