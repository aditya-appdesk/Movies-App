package com.example.retrofit.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit.models.Movie
import com.example.retrofit.models.Result
import com.example.retrofit.networkcall.RetrofitObject
import com.example.retrofit.repo.MovieRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel : ViewModel() {

    val repo = MovieRepo(RetrofitObject.getInstance())
    val popularMovie = MutableLiveData<List<Result>>()
    val topMovies = MutableLiveData<List<Result>>()

    fun getPopularMovie(apiKey: String) {
        repo.getPopularMovies(apiKey, object :
            Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    Log.i("Answer", response.body().toString())
                    popularMovie.value = response.body()?.results
                } else {
                    Log.i("Issue", response.message())
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.i("Error", t.message.toString())
            }
        })

    }

    fun getTopMovie(apiKey: String) {
        repo.getTopRatedMovies(apiKey, object :
            Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    Log.i("Answer Of TopRated", response.body().toString())
                    topMovies.value = response.body()?.results
                } else {
                    Log.i("Issue Of TopRated", response.message())
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.i("Error Of TopRated", t.message.toString())
            }
        })
    }

}