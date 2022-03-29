package com.example.retrofit.di

import android.app.Application
import com.example.retrofit.networkcall.RetrofitObject
import com.example.retrofit.repo.MovieRepo

class MyApplication : Application() {
    val retrofitObject by lazy { RetrofitObject.getInstance() }
    val repo by lazy { MovieRepo(retrofitObject) }
}