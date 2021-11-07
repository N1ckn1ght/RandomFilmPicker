package com.example.testp

data class Movie(val name: String, val direct: String = "", val year: String = "", val rating: String = "", val desc: String = "", val genre: List <String> = listOf(), val cast: List <String> = listOf()) {

}