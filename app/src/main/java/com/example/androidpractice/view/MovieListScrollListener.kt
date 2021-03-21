package com.example.androidpractice.view

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice.MoviePresenter

class MovieListScrollListener(
    private val pageSize: Int,
    private val linearLayoutManager: LinearLayoutManager,
    private val moviePresenter: MoviePresenter
) : RecyclerView.OnScrollListener() {

    private var currentPageNumber = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val lastVisibleMovieItemIndex = linearLayoutManager.findLastVisibleItemPosition()

        if (lastVisibleMovieItemIndex + 1 == pageSize * currentPageNumber) {
            currentPageNumber++ // TODO - Don't increment page number if the preseter fails to present movies
            moviePresenter.presentMovies(currentPageNumber)
        }
    }

}