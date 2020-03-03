package com.bolaware.architectureexample.network

import com.bolaware.architectureexample.data.MoviesResponse

public class NetworkCallback {

    interface NetworkCallback{
        fun onSuccess(moviesResponse: MoviesResponse)
        fun onError(message : String)
    }

}