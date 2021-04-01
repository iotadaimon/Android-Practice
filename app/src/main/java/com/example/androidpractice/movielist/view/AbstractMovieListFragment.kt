package com.example.androidpractice.movielist.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice.Constants
import com.example.androidpractice.MovieListContract
import com.example.androidpractice.R
import com.example.androidpractice.model.entity.Movie
import com.example.androidpractice.moviedetails.MovieDetailsActivity
import com.google.android.material.progressindicator.LinearProgressIndicator

abstract class AbstractMovieListFragment : Fragment(),
    MovieListContract.View {

    protected lateinit var presenter: MovieListContract.Presenter

    private lateinit var progressIndicator: LinearProgressIndicator
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            progressIndicator = it.findViewById(R.id.main_activity_progress_indicator)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)

        recyclerView = view.findViewById(R.id.movie_list_recycler_view)
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

class MovieAdapter(
    internal var movieList: List<Movie>,
    private val movieListView: MovieListContract.View
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

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

class MovieListScrollListener(
    private val pageSize: Int,
    private val linearLayoutManager: LinearLayoutManager,
    private val presenter: MovieListContract.Presenter
) : RecyclerView.OnScrollListener() {

    private var currentPageNumber = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val lastVisibleMovieItemIndex = linearLayoutManager.findLastVisibleItemPosition()

        if (lastVisibleMovieItemIndex + 1 == pageSize * currentPageNumber) {
            currentPageNumber++ // TODO - Don't increment page number if the preseter fails to present movies
            presenter.presentMovies(currentPageNumber)
        }
    }
}
