package com.example.retrofit.data.repo

import com.example.retrofit.data.models.Movie
import com.example.retrofit.data.models.Result
import com.example.retrofit.data.networkcall.RetrofitClient
import com.example.retrofit.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MovieRepo @Inject constructor(private val retrofitClient: RetrofitClient) {

    suspend fun getPopularMovies(): Response<Movie> = withContext(Dispatchers.IO) {
        val popularMovies = retrofitClient.getPopularMovies(Constants.API_KEY)
        popularMovies
    }

    suspend fun getTopRatedMovies(): Response<Movie> = withContext(Dispatchers.IO) {
        val topMovies = retrofitClient.getTopRated(Constants.API_KEY)
        topMovies
    }

    suspend fun getSingleMovies(movieId: Int): Response<Result> = withContext(Dispatchers.IO) {
        val singleMoviesDetail = retrofitClient.singleMovieDetails(movieId, Constants.API_KEY)
        singleMoviesDetail
    }
}