package com.example.cinemaapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.cinemaapp.R
import org.json.JSONException

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_item)

        val queue: RequestQueue = Volley.newRequestQueue(this)
        val url = "https://run.mocky.io/v3/ee22c8c2-4ffd-4d4e-bf70-c85ba8e2e72a"

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                val movies = ArrayList<Movie>()
                for (i in 0 until response.length()) {
                    try {
                        val movieObject = response.getJSONObject(i)
                        val movie = Movie(movieObject.getInt("id"),
                                            movieObject.getString("title"),
                                            movieObject.getString("description"),
                                            movieObject.getString("posterPath"),
                                            movieObject.getString("price"))
                        movies.add(movie)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                val adapter = MoviesAdapter(this, movies)
                val recyclerView: RecyclerView = findViewById(R.id.moviesRecyclerView)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = adapter
            },
            { error ->
                error.printStackTrace()
            })

        queue.add(jsonArrayRequest)
    }
}
