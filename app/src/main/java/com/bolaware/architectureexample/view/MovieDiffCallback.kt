package com.bolaware.architectureexample.view

import androidx.recyclerview.widget.DiffUtil
import com.bolaware.architectureexample.data.Movie

class MovieDiffCallback(val oldMovies : List<Movie>, val newMovies : List<Movie>) : DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldMovies.size
    }

    override fun getNewListSize(): Int {
        return newMovies.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovies[oldItemPosition].imdbID == newMovies[newItemPosition].imdbID
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovies[oldItemPosition].title == newMovies[newItemPosition].title
    }
}