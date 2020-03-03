package com.bolaware.architectureexample.network

import com.bolaware.architectureexample.data.MoviesResponse
import com.bolaware.architectureexample.network.MovieRetrofitFactory.MOVIE_TYPE
import com.bolaware.architectureexample.network.MovieRetrofitFactory.OMDB_API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    fun searchMovies(@Query("s") searchTerm : String,
                     @Query("apikey") apiKey : String = OMDB_API_KEY,
                     @Query("type") type : String = MOVIE_TYPE) : Call<MoviesResponse>
}