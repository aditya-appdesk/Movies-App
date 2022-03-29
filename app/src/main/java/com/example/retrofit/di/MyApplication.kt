package com.example.retrofit.di

import android.app.Application
import com.example.retrofit.networkcall.RetroFitObject
import com.example.retrofit.repo.MovieRepo

class MyApplication : Application() {
    val retrofitObject by lazy { RetroFitObject.getInstance() }
    val repo by lazy { MovieRepo(retrofitObject) }
}