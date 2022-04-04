package com.example.retrofit.networkcall

import com.example.retrofit.models.Movie
import com.example.retrofit.models.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RetrofitClient {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<Movie>

    @GET("3/movie/top_rated")
    suspend fun getTopRated(@Query("api_key") apiKey: String): Response<Movie>

    @GET("3/movie/{movie_id}")
    suspend fun singleMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<Result>
}