package com.example.androidpractice.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "liked_movies")
@Parcelize
data class Movie(

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("adult")
    var adult: Boolean? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    var releaseDate: String? = null,

    @Ignore
    @SerializedName("genre_ids")
    var genreIDs: List<Int>? = null,

    @PrimaryKey
    @SerializedName("id")
    var id: Int? = null,

    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    var originalTitle: String? = null,

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    var originalLanguage: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("popularity")
    var popularity: Float? = null,

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    var voteCount: Int? = null,

    @SerializedName("video")
    var video: Boolean? = null,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    var voteAverage: Float? = null
) : Parcelable