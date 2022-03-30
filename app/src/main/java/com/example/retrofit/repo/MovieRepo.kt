package com.example.retrofit.repo

import com.example.retrofit.models.Movie
import com.example.retrofit.models.Result
import com.example.retrofit.networkcall.RetrofitClient
import com.example.retrofit.utill.Constants
import retrofit2.Callback

class MovieRepo(private val retrofitClient: RetrofitClient) {

    fun getPopularMovies(
        apiResponseCallback: Callback<Movie>
    ) {
        retrofitClient.getPopularMovies(Constants.apiKey).enqueue(apiResponseCallback)
    }

    fun getTopRatedMovies(
        apiResponseCallback: Callback<Movie>
    ) {
        retrofitClient.getTopRated(Constants.apiKey).enqueue(apiResponseCallback)
    }

    fun getSingleMovies(
        int: Int,
        apiResponseCallback: Callback<Result>
    ) {
        retrofitClient.singleMovieDetails(int, Constants.apiKey).enqueue(apiResponseCallback)
    }


}