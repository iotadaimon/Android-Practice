package com.example.androidpractice.model.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("adult")
    val adult: Boolean?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("genre_ids")
    val genreIDs: List<Int>?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("popularity")
    val popularity: Number?,

    @SerializedName("vote_count")
    val voteCount: Int?,

    @SerializedName("video")
    val video: Boolean?,

    @SerializedName("vote_average")
    val voteAverage: Number?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readString(),
        null,           //        TODO("genreIDs"),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        null,           //        TODO("popularity"),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        null            //        TODO("voteAverage")
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(posterPath)
        dest?.writeBoolean(adult ?: false)
        dest?.writeString(overview)
        dest?.writeString(releaseDate)
        dest?.writeString(null)     //        dest?.write
        dest?.writeInt(id ?: -1)
        dest?.writeString(originalTitle)
        dest?.writeString(originalLanguage)
        dest?.writeString(title)
        dest?.writeString(backdropPath)
        dest?.writeString(null)         //        dest?.write
        dest?.writeBoolean(video ?: false)
        dest?.writeString(null)         //        dest?.write
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}