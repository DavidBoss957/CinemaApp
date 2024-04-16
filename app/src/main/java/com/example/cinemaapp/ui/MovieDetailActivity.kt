package com.example.cinemaapp.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.cinemaapp.R

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        // Recoge la información de la película que se pasó como extra
        val id = intent.getIntExtra("EXTRA_ID", 0) // Usar un valor predeterminado
        val title = intent.getStringExtra("EXTRA_TITLE") ?: "Title not available"
        val description = intent.getStringExtra("EXTRA_DESCRIPTION") ?: "Description not available"
        val posterPath = intent.getStringExtra("EXTRA_POSTER_PATH") ?: ""
        val price = intent.getStringExtra("EXTRA_PRICE") ?: "Price not available"

        // Encuentra las vistas por ID y configúralas con la información de la película
        val imageView: ImageView = findViewById(R.id.detail_movieImageView)
        val titleTextView: TextView = findViewById(R.id.detail_movieTitleTextView)
        val descriptionTextView: TextView = findViewById(R.id.detail_movieDescriptionTextView)
        val priceTextView: TextView = findViewById(R.id.detail_moviePriceTextView) // Asegúrate de que esta vista exista en tu XML

        // Configura las vistas
        titleTextView.text = title
        descriptionTextView.text = description
        priceTextView.text = "Price: $price"

        // Usa Glide para cargar la imagen
        Glide.with(this)
            .load(posterPath)
            .into(imageView)

    }
}
