package com.example.androidpractice.movielist.presenter

import com.example.androidpractice.MovieListContract
import com.example.androidpractice.MovieModel
import com.example.androidpractice.model.entity.Movie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MovieListPresenter @Inject constructor() : MovieListContract.Presenter {

    lateinit var model: MovieModel // Assign model after creation
    private lateinit var view: MovieListContract.View // Assign view after creation

    private var lastLoadedPageNumber = 0
    private var movies: MutableList<Movie> = mutableListOf() // List for caching
    private var isInProgress: Boolean = false

    override fun attachView(view: MovieListContract.View) {
        this.view = view
    }

    override fun presentMovies(upToPageNumber: Int, refresh: Boolean) {
        if (isInProgress) return
        if (refresh) clearCache()

        val pagesToLoadRange = (lastLoadedPageNumber + 1)..upToPageNumber
        if (pagesToLoadRange.isEmpty()) return // Return if upToPageNumber is less than or equal to last loaded page number

        view.showProgressIndicator()
        isInProgress = true

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
                    isInProgress = false
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