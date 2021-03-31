package com.example.androidpractice.movielist.presenter

import com.example.androidpractice.MovieListContract
import com.example.androidpractice.MovieModel
import com.example.androidpractice.model.entity.Movie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class AbstractMovieListPresenter : MovieListContract.Presenter {

    protected lateinit var model: MovieModel // Assign model on instantiation
    protected lateinit var view: MovieListContract.View  // Assign view after creation

    protected var lastLoadedPageNumber = 0
    protected var movies: MutableList<Movie> = mutableListOf()  // List for caching
    protected var isInProgress: Boolean = false

    override fun attachView(view: MovieListContract.View) {
        this.view = view
    }

    override fun presentMovies(upToPageNumber: Int, refresh: Boolean) {
        if (isInProgress) return    // Don't do anything if the operation is already in progress

        view.showProgressIndicator()
        isInProgress = true

        if (refresh) clearCache()

        val pagesToLoadRange = (lastLoadedPageNumber + 1)..upToPageNumber

        val moviePageSingleList = pagesToLoadRange
            .map { pageNumber -> model.getMoviePageRx(pageNumber).toObservable() }

        val moviesSingles = Observable.concat(moviePageSingleList)

        moviesSingles
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Movie>> {

                override fun onSubscribe(d: Disposable?) {}

                override fun onNext(t: List<Movie>?) {
                    t?.let { movies.addAll(it) }
                }

                override fun onError(e: Throwable?) {
                    view.hideProgressIndicator()
                    view.showErrorToast()
                }

                override fun onComplete() {
                    view.showMovies(movies)

                    lastLoadedPageNumber = upToPageNumber

                    view.hideProgressIndicator()
                    isInProgress = false
                }

            })
    }

    protected fun clearCache() {
        lastLoadedPageNumber = 0
        movies = mutableListOf()
    }

}