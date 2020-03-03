package com.bolaware.architectureexample.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bolaware.architectureexample.R
import kotlinx.android.synthetic.main.activity_main.*

const val INITIAL_SEARCH_TERM = "Game"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpView()

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

                    hideKeyboard()
                    return true
                }
                return false
            }
        })
    }


    private fun initAMovieSearch(){
        //viewModel.searchMovies(INITIAL_SEARCH_TERM)
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
