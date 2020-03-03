package com.bolaware.architectureexample

import com.bolaware.architectureexample.data.Movie
import com.bolaware.architectureexample.data.MoviesResponse

class MainContract {
    interface View{
        fun showProgressIndicator(show : Boolean)
        fun displayMovies(movies : List<Movie>)
        fun showToast(message : String)
    }

    interface ActionListener{
        fun searchMovies(query : String)
    }
}