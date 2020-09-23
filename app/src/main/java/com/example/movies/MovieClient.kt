package com.example.movies

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesClient {
    val BASE_URL = "https://api.themoviedb.org/3/"
    val service: ApiService


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ApiService::class.java)
    }

    fun fetchPopularMovies(page: Int = 1,
                           onSuccess: (moviesList: MutableList<Movie>) -> Unit,
                           onError: () -> Unit
    ){
        service.getPopularMovies(pageNumber = page)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    onError.invoke()
                }

                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    if(response.isSuccessful){
                        if(response.body() != null){
                            onSuccess.invoke(response.body()!!.movies)
                        } else {
                            onError.invoke()
                        }

                    }

                }

            })
    }
}