package com.example.androidpractice.movielist.view.base

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice.MovieListContract

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
