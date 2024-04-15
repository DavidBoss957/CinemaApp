package com.example.cinemaapp.ui

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

        // Inicializa el adaptador con una lista de películas vacía
        moviesAdapter = MoviesAdapter(this, mutableListOf())

        // Configura el layout manager y el adaptador del RecyclerView
        binding.moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.moviesRecyclerView.adapter = moviesAdapter

        loadMovies()
    }

    private fun loadMovies() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://run.mocky.io/v3/ee22c8c2-4ffd-4d4e-bf70-c85ba8e2e72a"

        // Cambia JsonArrayRequest por JsonObjectRequest
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                // Obtén el JSONArray asociado con la clave "movies"
                val moviesArray = response.getJSONArray("movies")
                val moviesList = mutableListOf<Movie>()
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
                e.printStackTrace()
            }
        }, { error ->
            error.printStackTrace()
        })

        queue.add(jsonObjectRequest)
    }
}
