package com.example.retrofit.data.repo

import com.example.retrofit.data.models.Movie
import com.example.retrofit.data.models.Result
import com.example.retrofit.data.networkcall.RetrofitClient
import com.example.retrofit.utils.Constants
import retrofit2.Response
import javax.inject.Inject

class MovieRepo @Inject constructor(private val retrofitClient: RetrofitClient) {

    suspend fun getPopularMovies(): Response<Movie> =
        retrofitClient.getPopularMovies(Constants.API_KEY)

    suspend fun getTopRatedMovies(): Response<Movie> = retrofitClient.getTopRated(Constants.API_KEY)

    suspend fun getSingleMovies(movieId: Int): Response<Result> =
        retrofitClient.singleMovieDetails(movieId, Constants.API_KEY)
}