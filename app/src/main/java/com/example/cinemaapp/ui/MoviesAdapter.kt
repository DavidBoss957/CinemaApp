package com.example.cinemaapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemaapp.databinding.MovieItemBinding

class MoviesAdapter(private val context: Context, private var movieList: List<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        with(holder.binding) {
            movieTitleTextView.text = movie.title
            Glide.with(context).load(movie.posterPath).into(movieImageView)
        }
    }

    override fun getItemCount(): Int = movieList.size

    fun updateMovies(movies: List<Movie>) {
        this.movieList = movies
        notifyDataSetChanged()
    }

    class MovieViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)
}
