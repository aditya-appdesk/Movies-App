package com.example.retrofit.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit.models.Movie
import com.example.retrofit.models.Result
import com.example.retrofit.repo.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repo: MovieRepo) : ViewModel() {
    val popularMovieLiveData = MutableLiveData<List<Result>>()
    val topMoviesLiveData = MutableLiveData<List<Result>>()
    val singleMoviesDetailLiveData = MutableLiveData<Result>()


    fun getPopularMoviesList() {
        repo.getPopularMovies(object :
            Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    Log.i("Answer", response.body().toString())
                    popularMovieLiveData.value = response.body()?.results
                } else {
                    Log.i("Issue", response.message())
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.i("Error", t.message.toString())
            }
        })
    }

    fun getTopMoviesList() {
        repo.getTopRatedMovies(object :
            Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    Log.i("Answer Of TopRated", response.body().toString())
                    topMoviesLiveData.value = response.body()?.results
                } else {
                    Log.i("Issue Of TopRated", response.message())
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.i("Error Of TopRated", t.message.toString())
            }
        })
    }

    fun getMovieDetails(int: Int) {
        repo.getSingleMovies(int, object :
            Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.isSuccessful) {
                    Log.i("Answer Of Single", response.body().toString())
                    singleMoviesDetailLiveData.value = response.body()
                } else {
                    Log.i("Issue Of Single", response.message())
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.i("Error Of Single", t.message.toString())
            }
        })
    }


}