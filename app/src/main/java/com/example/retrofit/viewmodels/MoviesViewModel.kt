package com.example.retrofit.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.models.Result
import com.example.retrofit.data.repo.MovieRepo
import com.example.retrofit.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repo: MovieRepo) : ViewModel() {
    val popularMovieLiveData = MutableLiveData<ApiResponse<List<Result>>>()
    val topMoviesLiveData = MutableLiveData<ApiResponse<List<Result>>>()
    val singleMoviesDetailLiveData = MutableLiveData<ApiResponse<Result>>()

    fun getPopularMoviesList() {
        viewModelScope.launch {
            try {
                val popularMovies = repo.getPopularMovies()
                if (popularMovies.isSuccessful) {
                    popularMovieLiveData.value = ApiResponse.Success(popularMovies.body()!!.results)
                } else {
                    popularMovieLiveData.value = ApiResponse.Error(popularMovies.message())
                }
            } catch (e: Exception) {
                popularMovieLiveData.value = e.message?.let { ApiResponse.Error(it) }
            }
        }
    }

    fun getTopMoviesList() {
        viewModelScope.launch {
            try {
                val topMovies = repo.getTopRatedMovies()
                if (topMovies.isSuccessful) {
                    topMoviesLiveData.value = ApiResponse.Success(topMovies.body()!!.results)
                } else {
                    topMoviesLiveData.value = ApiResponse.Error(topMovies.message())
                }
            } catch (e: Exception) {
                topMoviesLiveData.value = e.message?.let { ApiResponse.Error(it) }
            }
        }
    }

    fun getMovieDetails(int: Int) {
        viewModelScope.launch {
            try {
                val movieDetail = repo.getSingleMovies(int)
                if (movieDetail.isSuccessful) {
                    singleMoviesDetailLiveData.value = ApiResponse.Success(movieDetail.body()!!)
                } else {
                    singleMoviesDetailLiveData.value = ApiResponse.Error(movieDetail.message())
                }
            } catch (e: Exception) {
                singleMoviesDetailLiveData.value = e.message?.let { ApiResponse.Error(it) }
            }
        }
    }
}
