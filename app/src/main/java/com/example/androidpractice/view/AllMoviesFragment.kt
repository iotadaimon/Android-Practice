package com.example.androidpractice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice.presenter.AllMoviesPresenter
import com.example.androidpractice.MoviePresenter
import com.example.androidpractice.MovieView
import com.example.androidpractice.R
import com.example.androidpractice.model.TMDBModel
import com.example.androidpractice.model.TMDBService
import com.example.androidpractice.model.entity.Movie
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AllMoviesFragment : Fragment(), MovieView {

    private lateinit var presenter: MoviePresenter

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tmdbService = Retrofit.Builder()
            .baseUrl(TMDBService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TMDBService::class.java)

        presenter = AllMoviesPresenter(TMDBModel(tmdbService, TMDBModel.API_KEY), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_all_movies, container, false)

        recyclerView = view.findViewById(R.id.all_movies_recycler_view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.presentMovies()
    }

    override fun showMovies(movies: Deferred<List<Movie>>) {
        // TODO - Show a progress bar

        runBlocking {
            val moviesList = movies.await()
            recyclerView.adapter = MovieAdapter(moviesList)
        }
    }

    override fun showErrorToast() {
        Toast.makeText(
            context,
            resources.getText(R.string.tmdb_api_response_error_message),
            Toast.LENGTH_SHORT
        ).show()
    }
}