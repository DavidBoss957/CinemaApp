package com.example.cinemaapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemaapp.R
import com.example.cinemaapp.ui.Movie

class ItemAdapter(
    private val movies: List<Movie>,
    private val onClick: (Movie) -> Unit
) : RecyclerView.Adapter<ItemAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View, val onClick: (Movie) -> Unit) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        private val movieImageView: ImageView = view.findViewById(R.id.movieImageView)
        private var currentMovie: Movie? = null

        init {
            view.setOnClickListener {
                currentMovie?.let { onClick(it) }
            }
        }

        fun bind(movie: Movie) {
            currentMovie = movie
            titleTextView.text = movie.title
            // Aquí usamos Glide para cargar la imagen
            Glide.with(movieImageView.context).load(movie.posterPath).into(movieImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size
}
