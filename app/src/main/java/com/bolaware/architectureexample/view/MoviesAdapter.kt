package com.bolaware.architectureexample.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bolaware.architectureexample.R
import com.bolaware.architectureexample.data.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_list_item.view.*
import java.util.*


class MoviesAdapter (val movies : MutableList<Movie>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movies_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie){

            Picasso.get()
                .load(movie.posterUrl)
                .resize(60000, 2000)
                .centerInside()
                .placeholder(getRandomDrawbleColor())
                .into(itemView.bannerIV)

            ViewCompat.setTransitionName(itemView.bannerIV, movie.title)
        }
    }

    fun updateMovies(newPosts : List<Movie>){
        val diffCallback = MovieDiffCallback(movies, newPosts)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        movies.clear()
        movies.addAll(newPosts)
        diffResult.dispatchUpdatesTo(this)
    }
}


private val vibrantLightColorList = arrayOf(
    ColorDrawable(Color.parseColor("#9ACCCD")),
    ColorDrawable(Color.parseColor("#8FD8A0")),
    ColorDrawable(Color.parseColor("#CBD890")),
    ColorDrawable(Color.parseColor("#DACC8F")),
    ColorDrawable(Color.parseColor("#D9A790")),
    ColorDrawable(Color.parseColor("#D18FD9")),
    ColorDrawable(Color.parseColor("#FF6772")),
    ColorDrawable(Color.parseColor("#DDFB5C"))
)

fun getRandomDrawbleColor(): ColorDrawable {
    val idx = Random().nextInt(vibrantLightColorList.size)
    return vibrantLightColorList[idx]
}