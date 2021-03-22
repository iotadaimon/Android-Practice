package com.example.androidpractice.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice.Constants
import com.example.androidpractice.MoviePresenter
import com.example.androidpractice.MovieView
import com.example.androidpractice.R
import com.example.androidpractice.model.entity.Movie
import com.example.androidpractice.model.web.TMDBModel
import com.example.androidpractice.model.web.TMDBService
import com.example.androidpractice.presenter.AllMoviesPresenter
import com.google.android.material.progressindicator.LinearProgressIndicator
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AllMoviesFragment : Fragment(), MovieView {

    private lateinit var presenter: MoviePresenter

    private var progressIndicator: LinearProgressIndicator? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tmdbService = Retrofit.Builder()
            .baseUrl(TMDBService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TMDBService::class.java)

        presenter = AllMoviesPresenter(TMDBModel(tmdbService, Constants.API_KEY), this)
        progressIndicator = activity?.findViewById(R.id.main_activity_progress_indicator)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_all_movies, container, false)

        recyclerView = view.findViewById(R.id.all_movies_recycler_view)
        recyclerView.adapter = MovieAdapter(mutableListOf(), this)
        recyclerViewAdapter = recyclerView.adapter as MovieAdapter

        recyclerView.addOnScrollListener(
            MovieListScrollListener(
                Constants.MOVIE_LIST_PAGE_SIZE,
                recyclerView.layoutManager as LinearLayoutManager,
                presenter
            )
        )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.presentMovies()
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

    override fun showProgressIndicator() {
        progressIndicator?.show()
    }


    override fun hideProgressIndicator() {
        progressIndicator?.hide()
    }

    override fun showErrorToast() {
        Toast.makeText(
            context,
            resources.getText(R.string.tmdb_api_response_error_message),
            Toast.LENGTH_SHORT
        ).show()
    }

}

