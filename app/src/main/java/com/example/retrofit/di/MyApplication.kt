package com.example.retrofit.di

import android.app.Application
import com.example.retrofit.networkcall.RetrofitObject
import com.example.retrofit.repo.MovieRepo

class MyApplication : Application() {
    val appContainer = AppContainer()
}