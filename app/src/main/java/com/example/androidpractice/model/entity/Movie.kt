package com.example.androidpractice.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Movie(
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String? = null,

    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    val adult: Boolean? = null,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String? = null,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val releaseDate: String? = null,

    @ColumnInfo(name = "genre_ids")
    @SerializedName("genre_ids")
    val genreIDs: List<Int>? = null,

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int? = null,

    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    val originalTitle: String? = null,

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    val originalLanguage: String? = null,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String? = null,

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    val popularity: Number? = null,

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    val voteCount: Int? = null,

    @ColumnInfo(name = "video")
    @SerializedName("video")
    val video: Boolean? = null,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Number? = null
) : Parcelable