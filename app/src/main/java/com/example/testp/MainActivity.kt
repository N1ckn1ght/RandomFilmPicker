package com.example.testp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.view.View
import java.util.Random

class MainActivity : AppCompatActivity() {

    lateinit var movies : Array<String>
    var current_length: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movies = resources.getStringArray(R.array.movies)
        current_length = movies.size
    }

    fun onClick_next(view: View) {
        val title = findViewById<TextView>(R.id.title)
        if (current_length > 0) {
            var rand: Int = Random().nextInt(current_length)
            title.text = movies[rand]
            current_length -= 1
            swap(movies, rand, current_length)
        }
        else {
            title.text = "No unique films left!"
        }
    }

    fun onClick_clear(view: View) {
        val title = findViewById<TextView>(R.id.title)
        title.text = ""
        current_length = movies.size
    }

    private fun swap(arr: Array<String>, a: Int, b: Int) {
        var temp: String = arr[a]
        arr[a] = arr[b]
        arr[b] = temp
    }
}