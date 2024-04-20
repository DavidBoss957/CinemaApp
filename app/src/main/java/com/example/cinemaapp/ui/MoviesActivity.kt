package com.example.cinemaapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemaapp.databinding.ActivityMoviesBinding
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeRecyclerView()
        loadMovies()
    }

    private fun initializeRecyclerView() {
        moviesAdapter = MoviesAdapter(this, mutableListOf()) { movie ->
            val intent = Intent(this, MovieDetailActivity::class.java).apply {
                putExtra("EXTRA_ID", movie.id)
                putExtra("EXTRA_TITLE", movie.title)
                putExtra("EXTRA_DESCRIPTION", movie.description)
                putExtra("EXTRA_POSTER_PATH", movie.posterPath)
                putExtra("EXTRA_PRICE", movie.price)
            }
            startActivity(intent)
        }

        binding.moviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MoviesActivity)
            adapter = moviesAdapter
        }
    }


    private fun loadMovies() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://run.mocky.io/v3/7a25419c-0caa-4502-856e-f576841640e4"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                val moviesArray = response.getJSONArray("movies")
                val moviesList = ArrayList<Movie>()
                for (i in 0 until moviesArray.length()) {
                    val movieObject = moviesArray.getJSONObject(i)
                    val movie = Movie(
                        movieObject.getInt("id"),
                        movieObject.getString("title"),
                        movieObject.getString("description"),
                        movieObject.getString("posterPath"),
                        movieObject.getString("price")
                    )
                    moviesList.add(movie)
                }
                moviesAdapter.updateMovies(moviesList)
            } catch (e: JSONException) {
                Log.e("MoviesActivity", "JSON parsing error", e)
            }
        }, { error ->
            Log.e("MoviesActivity", "Volley error", error)
        })

        queue.add(jsonObjectRequest)
    }
}
