package com.example.retrofit.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.models.Result
import com.example.retrofit.repo.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repo: MovieRepo) : ViewModel() {
    val popularMovieLiveData = MutableLiveData<List<Result>>()
    val topMoviesLiveData = MutableLiveData<List<Result>>()
    val singleMoviesDetailLiveData = MutableLiveData<Result>()

    fun getPopularMoviesList() {
        viewModelScope.launch {
            val popularMovies = repo.getPopularMovies()
            if (popularMovies.isSuccessful) {
                popularMovieLiveData.value = popularMovies.body()?.results
            }
        }
    }

    fun getTopMoviesList() {
        viewModelScope.launch {
            val topMovies = repo.getTopRatedMovies()
            if (topMovies.isSuccessful) {
                topMoviesLiveData.value = topMovies.body()?.results
            }
        }
    }

    fun getMovieDetails(int: Int) {
        viewModelScope.launch {
            val movieDetail = repo.getSingleMovies(int)
            if (movieDetail.isSuccessful) {
                singleMoviesDetailLiveData.value = movieDetail.body()
            }
        }
    }
}
