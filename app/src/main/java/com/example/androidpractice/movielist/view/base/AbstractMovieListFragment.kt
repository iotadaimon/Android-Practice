package com.example.androidpractice.movielist.view.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidpractice.Constants
import com.example.androidpractice.MovieListContract
import com.example.androidpractice.R
import com.example.androidpractice.databinding.FragmentMovieListBinding
import com.example.androidpractice.model.entity.Movie
import com.example.androidpractice.moviedetails.MovieDetailsActivity
import com.google.android.material.progressindicator.LinearProgressIndicator

abstract class AbstractMovieListFragment : Fragment(),
    MovieListContract.View {

    protected lateinit var presenter: MovieListContract.Presenter

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!! // This property is only valid between onCreateView and onDestroyView.

    private lateinit var progressIndicator: LinearProgressIndicator

    private lateinit var recyclerViewAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        val view = binding.root

        activity?.let {
            progressIndicator = it.findViewById(R.id.main_activity_progress_indicator)
        }

        binding.movieListRecyclerView.adapter = MovieAdapter(mutableListOf(), this)
        recyclerViewAdapter = binding.movieListRecyclerView.adapter as MovieAdapter

        binding.movieListRecyclerView.addOnScrollListener(
            MovieListScrollListener(
                Constants.MOVIE_LIST_PAGE_SIZE,
                binding.movieListRecyclerView.layoutManager as LinearLayoutManager,
                presenter
            )
        )

        return view
    }

    override fun showMovies(movies: List<Movie>) {
        recyclerViewAdapter.movieList = movies
        recyclerViewAdapter.notifyDataSetChanged()
    }

    override fun showMovieDetails(movie: Movie) {
        val intent = Intent(context, MovieDetailsActivity::class.java)
        intent.putExtra(MovieDetailsActivity.DATA_MOVIE, movie)
        startActivity(intent)
    }

    override fun showProgressIndicator() = progressIndicator.show()

    override fun hideProgressIndicator() = progressIndicator.hide()

    protected fun showToast(message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}
