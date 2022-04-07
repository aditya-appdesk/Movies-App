package com.example.retrofit.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.models.Result
import com.example.retrofit.data.repo.MovieRepo
import com.example.retrofit.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repo: MovieRepo) : ViewModel() {
    val popularMovieLiveData = MutableLiveData<ApiResponse<List<Result>>>()
    val topMoviesLiveData = MutableLiveData<ApiResponse<List<Result>>>()
    val singleMoviesDetailLiveData = MutableLiveData<ApiResponse<Result>>()

    fun getPopularMoviesList() {
        viewModelScope.launch {
            popularMovieLiveData.value = ApiResponse.Loading()
            delay(1000)
            try {
                val popularMovies = repo.getPopularMovies()
                if (popularMovies.isSuccessful) {
                    popularMovieLiveData.value = ApiResponse.Success(popularMovies.body()!!.results)
                } else {
                    popularMovieLiveData.value = ApiResponse.Error(popularMovies.message())
                }
            } catch (e: Exception) {
                popularMovieLiveData.value = e.message?.let { error ->
                    ApiResponse.Error(error)
                }
            }
        }
    }

    fun getTopMoviesList() {

        viewModelScope.launch {
            topMoviesLiveData.value = ApiResponse.Loading()
            delay(1000)
            try {
                val topMovies = repo.getTopRatedMovies()
                if (topMovies.isSuccessful) {
                    topMoviesLiveData.value = ApiResponse.Success(topMovies.body()!!.results)
                } else {
                    topMoviesLiveData.value = ApiResponse.Error(topMovies.message())
                }
            } catch (e: Exception) {
                topMoviesLiveData.value = e.message?.let { error ->
                    ApiResponse.Error(error)
                }
            }
        }
    }

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            singleMoviesDetailLiveData.value = ApiResponse.Loading()
            delay(1000)
            try {
                val movieDetail = repo.getSingleMovies(movieId)
                if (movieDetail.isSuccessful) {
                    singleMoviesDetailLiveData.value = ApiResponse.Success(movieDetail.body()!!)
                } else {
                    singleMoviesDetailLiveData.value = ApiResponse.Error(movieDetail.message())
                }
            } catch (e: Exception) {
                singleMoviesDetailLiveData.value = e.message?.let { error ->
                    ApiResponse.Error(error)
                }
            }
        }
    }
}
