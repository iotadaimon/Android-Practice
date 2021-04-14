package com.example.androidpractice.dagger

import javax.inject.Qualifier

/**
 * Constants
 */

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class DatabaseName

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiKey

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class PosterRepositoryBaseUrl

/**
 * Models
 */

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class WebModel

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalImmutableModel

/**
 * Presenters
 */

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class WebMoviePresenter

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalMoviePresenter
