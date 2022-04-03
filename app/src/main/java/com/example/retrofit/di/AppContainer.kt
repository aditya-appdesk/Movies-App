package com.example.retrofit.di

import com.example.retrofit.networkcall.RetrofitObject
import com.example.retrofit.repo.MovieRepo

class AppContainer {
    private val retrofitClient by lazy { RetrofitObject.getInstance() }
    val repo by lazy { MovieRepo(retrofitClient) }
}