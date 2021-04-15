package com.example.androidpractice.movielist.view.base

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice.MovieListContract

class MovieListScrollListener(
    private val pageSize: Int,
    private val linearLayoutManager: LinearLayoutManager,
    private val presenter: MovieListContract.Presenter
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val movieItemsLoaded = linearLayoutManager.itemCount
        val lastVisibleMovieItemIndex = linearLayoutManager.findLastVisibleItemPosition()

        if (lastVisibleMovieItemIndex + 1 == movieItemsLoaded) {
            val currentPageNumber = movieItemsLoaded / pageSize
            presenter.presentMovies(currentPageNumber + 1) // Present next page if all loaded movies have been shown
        }
    }
}
