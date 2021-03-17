package com.example.androidpractice.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice.R

// TODO - Accept a map of properties?
class MovieDetailsAdapter(private val movieProperties: List<Pair<String, Any?>>) :
    RecyclerView.Adapter<MovieDetailsAdapter.MoviePropertyViewHolder>() {

    private companion object {
        const val NULL_DATA_PROPERTY_VALUE = "No data"
    }

    // Describes an item view and its place within the RecyclerView
    class MoviePropertyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val propertyNameTextView: TextView =
            view.findViewById(R.id.movie_property_name_textView)
        private val propertyValueTextView: TextView =
            view.findViewById(R.id.movie_property_value_textView)

        fun bind(movieProperty: Pair<String, Any?>) {
            propertyNameTextView.text = movieProperty.first
            propertyValueTextView.text = movieProperty.second?.toString() ?: NULL_DATA_PROPERTY_VALUE
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePropertyViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_movie_property, parent, false)

        return MoviePropertyViewHolder(view)
    }

    // Returns size of data list
    override fun getItemCount() = movieProperties.size

    // Displays data at a certain position
    override fun onBindViewHolder(holderMovieProperty: MoviePropertyViewHolder, position: Int) {
        holderMovieProperty.bind(movieProperties[position])
    }

}
