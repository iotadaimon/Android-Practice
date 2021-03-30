package com.example.androidpractice.presenter

import com.example.androidpractice.MovieModel
import com.example.androidpractice.MoviePresenter
import com.example.androidpractice.MovieView
import com.example.androidpractice.model.entity.Movie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class AbstractMovieListPresenter : MoviePresenter {

    protected lateinit var model: MovieModel // Assign model on instantiation
    protected lateinit var view: MovieView

    protected var lastLoadedPageNumber = 0
    protected var movies: MutableList<Movie> = mutableListOf()
    protected var isInProgress: Boolean = false

    override fun attachView(view: MovieView) {
        this.view = view
    }

    override fun presentMovies(upToPageNumber: Int, refresh: Boolean) {
        if (isInProgress) return    // Don't do anything if the operation is already in progress

        view.showProgressIndicator()
        isInProgress = true

        if (refresh) clearCache()

        val pageRange = (lastLoadedPageNumber + 1)..upToPageNumber

        val moviePageSingleList = pageRange
            .map { pageNumber -> model.getMoviePageRx(pageNumber).toObservable() }

        val moviesSingles = Observable.concat(moviePageSingleList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        moviesSingles.subscribe(object : Observer<List<Movie>> {

            override fun onSubscribe(d: Disposable?) {}

            override fun onNext(t: List<Movie>?) {
                t?.let { movies.addAll(it) }
            }

            override fun onError(e: Throwable?) {
                view.hideProgressIndicator()
                view.showErrorToast()
            }

            override fun onComplete() {
                view.hideProgressIndicator()
                view.showMovies(movies)

                lastLoadedPageNumber = upToPageNumber
                isInProgress = false
            }

        })
    }

    protected fun clearCache() {
        lastLoadedPageNumber = 0
        movies = mutableListOf()
    }

}