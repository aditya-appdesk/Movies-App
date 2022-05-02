package com.example.retrofit.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.models.Result
import com.example.retrofit.data.repo.MovieRepo
import com.example.retrofit.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repo: MovieRepo) : ViewModel() {
    private val flowOfPopularMovie =
        MutableStateFlow<ApiResponse<List<Result>>>(ApiResponse.Loading())
    val _popularMovie: StateFlow<ApiResponse<List<Result>>> = flowOfPopularMovie
    private val flowTopMovies = MutableStateFlow<ApiResponse<List<Result>>>(ApiResponse.Loading())
    val _topMovies: StateFlow<ApiResponse<List<Result>>> = flowTopMovies
    private val flowSingleMoviesDetail =
        MutableStateFlow<ApiResponse<Result>>(ApiResponse.Loading())
    val _singleMoviesDetail: StateFlow<ApiResponse<Result>> = flowSingleMoviesDetail


    fun getTopMoviesList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val topMovies = repo.getTopRatedMovies()
                    if (topMovies.isSuccessful) {
                        flowTopMovies.emit(ApiResponse.Success(topMovies.body()!!.results))
                    } else {
                        flowTopMovies.emit(ApiResponse.Error(topMovies.message()))
                    }
                } catch (e: Exception) {
                    flowTopMovies.emit(ApiResponse.Error(e.message!!))
                }
            }
        }
    }

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                flowSingleMoviesDetail.emit(ApiResponse.Loading())
                try {
                    val movieDetail = repo.getSingleMovies(movieId)
                    if (movieDetail.isSuccessful) {
                        flowSingleMoviesDetail.emit(ApiResponse.Success(movieDetail.body()!!))
                    } else {
                        flowSingleMoviesDetail.emit(ApiResponse.Error(movieDetail.message()))
                    }
                } catch (e: Exception) {
                    flowSingleMoviesDetail.emit(ApiResponse.Error(e.message!!))
                }
            }
        }
    }
    fun getPopularMoviesList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val popularMovies = repo.getPopularMovies()
                    if (popularMovies.isSuccessful) {
                        flowOfPopularMovie.emit(ApiResponse.Success(popularMovies.body()!!.results))
                    } else {
                        flowOfPopularMovie.emit(ApiResponse.Error(popularMovies.message()))
                    }
                } catch (e: Exception) {
                    flowOfPopularMovie.emit(ApiResponse.Error(e.message!!))
                }
            }
        }
    }
}
