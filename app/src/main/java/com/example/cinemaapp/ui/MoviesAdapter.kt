package com.example.cinemaapp.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemaapp.R
import com.example.cinemaapp.databinding.MovieItemBinding

class MoviesAdapter(private val context: Context, private val movies: MutableList<Movie>, private val listener: (Movie) -> Unit) :

    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        with(holder) {
            Glide.with(itemView.context)
                .load(movie.posterPath)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .centerCrop()
                .into(imageView)

            titleTextView.text = movie.title

            itemView.setOnClickListener { listener(movie) }
        }
    }

    override fun getItemCount() = movies.size

    fun updateMovies(moviesList: List<Movie>) {
        movies.clear()
        movies.addAll(moviesList)
        notifyDataSetChanged()
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.movieImageView)
        val titleTextView: TextView = view.findViewById(R.id.movieTitleTextView)
    }


}
