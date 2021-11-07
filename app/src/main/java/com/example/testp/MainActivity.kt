package com.example.testp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.view.View
import com.google.gson.Gson
import java.util.Random
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    lateinit var data : Movies
    lateinit var index : Array <Int>
    var size : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stream = resources.openRawResource(R.raw.movies)
        val gson = Gson()
        data = gson.fromJson(InputStreamReader(stream), Movies::class.java)
        Log.d("movie", "Loaded ${data.movies.size} movies")
        size = data.movies.size
        index = Array(size) {it}
    }

    fun onClick_next(view: View) {
        val name = findViewById<TextView>(R.id.name)
        val direct = findViewById<TextView>(R.id.direct)
        val year = findViewById<TextView>(R.id.year)
        val rating = findViewById<TextView>(R.id.rating)
        val genre = findViewById<TextView>(R.id.genre)
        val desc = findViewById<TextView>(R.id.desc)
        val cast = findViewById<TextView>(R.id.cast)

        val movie = getRandom()
        name.text = movie.name
        direct.text = movie.direct
        year.text = movie.year
        rating.text = movie.rating
        genre.text = movie.genre.joinToString()
        desc.text = movie.desc
        cast.text = movie.cast.joinToString(separator = "\n")
    }

    fun onClick_clear(view: View) {
        val name = findViewById<TextView>(R.id.name)
        val direct = findViewById<TextView>(R.id.direct)
        val year = findViewById<TextView>(R.id.year)
        val rating = findViewById<TextView>(R.id.rating)
        val genre = findViewById<TextView>(R.id.genre)
        val desc = findViewById<TextView>(R.id.desc)
        val cast = findViewById<TextView>(R.id.cast)

        clear()
        name.text = getString(R.string.init)
        direct.text = ""
        year.text = ""
        rating.text = ""
        genre.text = ""
        desc.text = ""
        cast.text = ""
    }

    private fun getRandom() : Movie
    {
        if (size < 1)
        {
            return Movie(getString(R.string.end))
        }
        var rnd: Int = Random().nextInt(size)
        size--
        index_swap(rnd, size)
        return data.movies[index[size]]
    }

    private fun index_swap(a: Int, b: Int) {
        if (a == b)
        {
            return
        }
        index[a] = index[a] + index[b]
        index[b] = index[a] - index[b]
        index[a] = index[a] - index[b]
    }

    private fun clear()
    {
        size = data.movies.size
    }
}