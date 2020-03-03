package com.bolaware.architectureexample.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bolaware.architectureexample.MainContract
import com.bolaware.architectureexample.R
import com.bolaware.architectureexample.data.Movie
import com.bolaware.architectureexample.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

const val INITIAL_SEARCH_TERM = "Game"

class MainActivity : AppCompatActivity(), MainContract.View {

    val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpView()

        setUpObserver()

        initAMovieSearch()
    }

    private fun setUpView(){
        val snapHelper = androidx.recyclerview.widget.PagerSnapHelper()
        moviesRV.onFlingListener = null
        snapHelper.attachToRecyclerView(moviesRV)

        moviesRV.layoutManager =
            CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        moviesRV.adapter = MoviesAdapter(mutableListOf())

        searchET.setOnEditorActionListener( object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || event?.action == KeyEvent.ACTION_DOWN
                    && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                    viewModel.searchMovies(searchET.text.toString())
                    hideKeyboard()
                    return true
                }
                return false
            }
        })
    }

    private fun setUpObserver(){
        viewModel.moviesLd.observe(this, Observer {
            it?.let {
                (moviesRV.adapter as MoviesAdapter?)?.updateMovies(it)
            }
        })

        viewModel.errorLd.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.progressIndicatorLd.observe(this, Observer {
            progressBar.visibility = if(it == true){
                View.VISIBLE
            } else{
                View.GONE
            }
        })
    }

    private fun initAMovieSearch(){
        viewModel.searchMovies(INITIAL_SEARCH_TERM)
    }

    override fun showProgressIndicator(show: Boolean) {

    }

    override fun displayMovies(movies: List<Movie>) {

    }

    override fun showToast(message: String) {

    }
}


fun Activity.hideKeyboard() {
    try {
        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = this.currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
    catch (e : Exception) {
        e.printStackTrace()
    }
}
