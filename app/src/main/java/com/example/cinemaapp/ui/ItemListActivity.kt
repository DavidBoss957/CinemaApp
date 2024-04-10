package com.example.cinemaapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.cinemaapp.R
import org.json.JSONObject

class ItemListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ItemAdapter(listOf(), this::onMovieClicked)
        recyclerView.adapter = adapter

        loadMoviesFromApi()
    }

    private fun onMovieClicked(movie: Movie) {
        // Aquí manejas el clic en cada película, tal vez iniciando una nueva Activity con detalles.
    }


    private fun loadMoviesFromApi() {
        val url = "https://api.themoviedb.org/3/movie/popular?api_key=YOUR_API_KEY"

        // Solicitar un string response del URL proporcionado.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                // Parsea la respuesta en tu lista de películas y notifica al adaptador
                val responseObj = JSONObject(response)
                val moviesArray = responseObj.getJSONArray("results")
                for (i in 0 until moviesArray.length()) {
                    val movieObj = moviesArray.getJSONObject(i)
                    val movie = Movie(
                        movieObj.getInt("id"),
                        movieObj.getString("title"),
                        movieObj.getString("poster_path"),
                        movieObj.getString("overview"),
                        movieObj.getString("release_date")
                    )
                    movies.add(movie)
                }
                adapter.notifyDataSetChanged()
            },
            { error ->
                // Manejar errores
                Toast.makeText(this, "Error loading movies: ${error.message}", Toast.LENGTH_LONG).show()
            })

        // Añadir la solicitud a la cola de Volley
        Volley.newRequestQueue(this).add(stringRequest)
    }

    private fun parseMovies(response: String) {
        // Analiza la respuesta y crea una lista de objetos Movie
    }
}
