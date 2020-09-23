package com.example.movies

import com.google.gson.annotations.SerializedName


data class MoviesResponse (
    @SerializedName("results") val movies : MutableList<com.example.movies.Movie>,
    @SerializedName("page") val currentPage : Int,
    @SerializedName("total_pages") val totalPages : Int
)