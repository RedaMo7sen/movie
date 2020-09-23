package com.example.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.android.synthetic.main.card_movie.view.*

class MoviesAdapter(private var moviesList: MutableList<Movie>?,val m:MainActivity) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(movie: Movie) {
            itemView.item_movie_release_date.text = movie.releaseDate
            itemView.item_movie_rating.text = movie.rating.toString()
            itemView.item_movie_title.text = movie.title
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(CenterCrop())
                .into(itemView.item_movie_poster)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder
            = MovieViewHolder(
        LayoutInflater.from(parent.context).inflate(
        R.layout.card_movie,
        parent,
        false
    ))
    override fun getItemCount(): Int = moviesList!!.size
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var movie = moviesList!![position]
        holder.onBind(movie)

        holder.itemView.setOnClickListener {
            val intent = Intent(m,MainActivity2::class.java)
            intent.putExtra("title", moviesList!![position].title.toString())
            intent.putExtra("rate", moviesList!![position].rating.toString())
            intent.putExtra("ov", moviesList!![position].overview.toString())

            m.startActivity(intent)
        }

    }
    fun appendMovies(movies: List<Movie>){
        this.moviesList!!.addAll(movies)
        notifyItemRangeInserted(
            this.moviesList!!.size,
            moviesList!!.size - 1
        )
    }

}