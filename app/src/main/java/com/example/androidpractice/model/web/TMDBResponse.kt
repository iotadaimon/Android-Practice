package com.example.androidpractice.model.web

import com.example.androidpractice.model.entity.Movie
import com.google.gson.annotations.SerializedName

data class TMDBResponse(
    @SerializedName("page")
    val page: Int?,

    @SerializedName("results")
    val results: List<Movie>?,

    @SerializedName("total_results")
    val totalResults: Int?,

    @SerializedName("total_pages")
    val totalPages: Int?
)