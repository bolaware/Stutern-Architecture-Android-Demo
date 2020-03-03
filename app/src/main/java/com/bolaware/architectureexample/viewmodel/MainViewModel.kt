package com.bolaware.architectureexample.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bolaware.architectureexample.data.Movie
import com.bolaware.architectureexample.data.MoviesResponse
import com.bolaware.architectureexample.network.NetworkCallback
import com.bolaware.architectureexample.network.NetworkRepo

class MainViewModel : ViewModel() {
    val moviesMutableLd = MutableLiveData<List<Movie>>()
    val errorMutableLd = MutableLiveData<String>()
    val progressIndicatorMutableLd = MutableLiveData<Boolean>()

    val repo by lazy { NetworkRepo() }

    val moviesLd : LiveData<List<Movie>>
        get() = moviesMutableLd

    val errorLd : LiveData<String>
        get() = errorMutableLd

    val progressIndicatorLd : LiveData<Boolean>
        get() = progressIndicatorMutableLd

    fun searchMovies(query : String){
        progressIndicatorMutableLd.value = true

        repo.fetchMovie(query, object : NetworkCallback.NetworkCallback{
            override fun onSuccess(moviesResponse: MoviesResponse) {
                progressIndicatorMutableLd.value = false
                moviesMutableLd.value = moviesResponse.movies
            }

            override fun onError(message: String) {
                progressIndicatorMutableLd.value = false
                errorMutableLd.value = message
            }
        })
    }
}