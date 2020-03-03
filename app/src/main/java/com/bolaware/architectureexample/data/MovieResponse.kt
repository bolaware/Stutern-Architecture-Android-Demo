package com.bolaware.architectureexample.data

import com.google.gson.annotations.SerializedName

data class MoviesResponse (
    @SerializedName("Search")
    var movies : List<Movie>,
    val totalResults : String,
    @SerializedName("Response")
    var response : Boolean
)

data class Movie (
    @SerializedName("Title")
    val title : String,
    @SerializedName("Year")
    val year : String,
    val imdbID : String,
    @SerializedName("Type")
    val type : String,
    @SerializedName("Poster")
    val posterUrl : String
)