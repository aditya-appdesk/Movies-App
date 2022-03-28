package com.example.retrofit.networkcall

import com.example.retrofit.models.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitClient {

    @GET("3/movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<Movie>

    @GET("3/movie/top_rated")
    fun getTopRated(@Query("api_key") apiKey: String): Call<Movie>

    @GET("3/movie/")
    fun singleMovieDetails(
        @Query("{movie_id}") id: Int,
        @Query("api_key") apiKey: String
    ): Call<Movie>
}