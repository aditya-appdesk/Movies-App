package com.example.retrofit.repo

import com.example.retrofit.models.Movie
import com.example.retrofit.models.Result
import com.example.retrofit.networkcall.RetrofitClient
import retrofit2.Callback

class MovieRepo(private val retrofitClient: RetrofitClient) {

    //val repo = MovieRepo(RetroFitObject.getInstance())
    fun getPopularMovies(
        apiKey: String,
        apiResponseCallback: Callback<Movie>
    ) {
        retrofitClient.getPopularMovies(apiKey).enqueue(apiResponseCallback)
    }

    fun getTopRatedMovies(
        apiKey: String,
        apiResponseCallback: Callback<Movie>
    ) {
        retrofitClient.getTopRated(apiKey).enqueue(apiResponseCallback)
    }

    fun getSingleMovies(
        int: Int,
        apiKey: String,
        apiResponseCallback: Callback<Result>
    ) {
        retrofitClient.singleMovieDetails(int, apiKey).enqueue(apiResponseCallback)
    }


}