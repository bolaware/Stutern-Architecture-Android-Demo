package com.bolaware.architectureexample.network

import com.bolaware.architectureexample.data.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkRepo {

    private val service by lazy {
        MovieRetrofitFactory.service
    }

    fun fetchMovie(query : String, callback: NetworkCallback.NetworkCallback){

        val call = service.searchMovies(query)

        call.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if(response.isSuccessful){
                    response?.body()?.let {moviesResponse ->
                        callback.onSuccess(moviesResponse)
                    }
                } else{
                    callback.onError("Error fetching movie")
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                t.message?.let {
                    callback.onError(it)
                }
            }
        })
    }
}