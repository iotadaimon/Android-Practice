package com.example.androidpractice.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice.R
import com.example.androidpractice.model.entity.Movie

class AllMoviesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO - fix types
        val movieList = arguments?.getParcelableArrayList<Movie>("data")

        val recyclerView: RecyclerView? = view?.findViewById(R.id.all_movies_recycler_view)
        recyclerView?.adapter = MovieAdapter(movieList!!.toList())

        return inflater.inflate(R.layout.fragment_all_movies, container, false)
    }

}