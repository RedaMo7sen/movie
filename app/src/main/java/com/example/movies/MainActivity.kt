package com.example.movies

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
var currentPageNumber = 3
lateinit var moviesAdapter: MoviesAdapter
lateinit var llm: LinearLayoutManager
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    moviesAdapter = MoviesAdapter(mutableListOf(),this)
    llm = LinearLayoutManager(this,
        LinearLayoutManager.VERTICAL,
        false)
    rv_movies.adapter = moviesAdapter
    rv_movies.layoutManager = llm
    getPopularMovies()
    // This is in development branch
}

fun getPopularMovies(){
    Log.d("Popular Movies",
        "here" )
    MoviesClient.fetchPopularMovies(
        currentPageNumber,
        ::onPopularMoviesFetched,
        ::onError)
}

private fun onError() {
    Toast
        .makeText(this, "Failed to fetch movies", Toast.LENGTH_SHORT)
        .show()
}

private fun onPopularMoviesFetched(list: MutableList<Movie>) {
    moviesAdapter.appendMovies(list)
    attachOnScrollListener()
}

fun attachOnScrollListener(){
    rv_movies.addOnScrollListener(object: RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val totalItems = llm.itemCount
            val visibleItemsCount = llm.childCount
            val firstVisibleItem = llm.findLastVisibleItemPosition()

            if(firstVisibleItem + visibleItemsCount >= totalItems/2){
                rv_movies.removeOnScrollListener(this)
                currentPageNumber++
                getPopularMovies()
            }
        }
    })
}

}