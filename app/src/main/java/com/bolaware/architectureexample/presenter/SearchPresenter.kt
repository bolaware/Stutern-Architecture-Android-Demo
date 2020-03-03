package com.bolaware.architectureexample.presenter

import com.bolaware.architectureexample.MainContract
import com.bolaware.architectureexample.data.MoviesResponse
import com.bolaware.architectureexample.network.NetworkCallback
import com.bolaware.architectureexample.network.NetworkRepo

class SearchPresenter(val mView : MainContract.View) : MainContract.ActionListener {

    private val networkRepo by lazy { NetworkRepo() }

    override fun searchMovies(query: String) {
        mView.showProgressIndicator(true)

        networkRepo.fetchMovie(query, object : NetworkCallback.NetworkCallback{
            override fun onSuccess(moviesResponse: MoviesResponse) {
                mView.showProgressIndicator(false)

                mView.displayMovies(moviesResponse.movies)
            }

            override fun onError(message: String) {
                mView.showToast(message)
            }
        })
    }
}