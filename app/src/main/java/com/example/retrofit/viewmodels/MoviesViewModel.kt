package com.example.retrofit.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.models.Result
import com.example.retrofit.data.repo.MovieRepo
import com.example.retrofit.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repo: MovieRepo) : ViewModel() {
    private val popularMovieLiveData = MutableStateFlow<ApiResponse<List<Result>>>(ApiResponse.Loading())
    val _popularMovieLiveData: StateFlow<ApiResponse<List<Result>>> = popularMovieLiveData
    private val topMoviesLiveData = MutableLiveData<ApiResponse<List<Result>>>()
    val _topMoviesLiveData: LiveData<ApiResponse<List<Result>>> = topMoviesLiveData
    private val singleMoviesDetailLiveData = MutableLiveData<ApiResponse<Result>>()
    val _singleMoviesDetailLiveData: LiveData<ApiResponse<Result>> = singleMoviesDetailLiveData


/*
    fun getPopularMoviesList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                popularMovieLiveData.postValue(ApiResponse.Loading())
                try {
                    val popularMovies = repo.getPopularMovies()
                    if (popularMovies.isSuccessful) {
                        popularMovieLiveData.postValue(ApiResponse.Success(popularMovies.body()!!.results))
                    } else {
                        popularMovieLiveData.postValue(ApiResponse.Error(popularMovies.message()))
                    }
                } catch (e: Exception) {
                    popularMovieLiveData.postValue(e.message?.let { error ->
                        ApiResponse.Error(error)
                    })
                }
            }
        }
    }
*/

    fun getTopMoviesList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                topMoviesLiveData.postValue(ApiResponse.Loading())
                try {
                    val topMovies = repo.getTopRatedMovies()
                    if (topMovies.isSuccessful) {
                        topMoviesLiveData.postValue(ApiResponse.Success(topMovies.body()!!.results))
                    } else {
                        topMoviesLiveData.postValue(ApiResponse.Error(topMovies.message()))
                    }
                } catch (e: Exception) {
                    topMoviesLiveData.postValue(e.message?.let { error ->
                        ApiResponse.Error(error)
                    })
                }
            }
        }
    }

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                singleMoviesDetailLiveData.postValue(ApiResponse.Loading())
                try {
                    val movieDetail = repo.getSingleMovies(movieId)
                    if (movieDetail.isSuccessful) {
                        singleMoviesDetailLiveData.postValue(ApiResponse.Success(movieDetail.body()!!))
                    } else {
                        singleMoviesDetailLiveData.postValue(ApiResponse.Error(movieDetail.message()))
                    }
                } catch (e: Exception) {
                    singleMoviesDetailLiveData.postValue(e.message?.let { error ->
                        ApiResponse.Error(error)
                    })
                }
            }
        }
    }
    fun getPopularMoviesList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
             //   popularMovieLiveData.emit(ApiResponse.Loading())
                try {
                    val popularMovies = repo.getPopularMovies()
                    if (popularMovies.isSuccessful) {
                        popularMovieLiveData.emit(ApiResponse.Success(popularMovies.body()!!.results))
                    } else {
                        popularMovieLiveData.emit(ApiResponse.Error(popularMovies.message()))
                    }
                } catch (e: Exception) {
                    popularMovieLiveData.emit(ApiResponse.Error(e.message!!))
                }
            }
        }
    }

}
