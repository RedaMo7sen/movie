package com.example.movies

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey : String = "61b47d5e8c85d3d43f7e8650f6f3ed44",
        @Query("page") pageNumber: Int = 1
    ) : Call<MoviesResponse>
}