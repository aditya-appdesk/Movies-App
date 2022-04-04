package com.example.retrofit.di

import com.example.retrofit.networkcall.RetrofitClient
import com.example.retrofit.networkcall.RetrofitObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyModule {

    @Singleton
    @Provides
    fun getInstanceRetrofit(): RetrofitClient = RetrofitObject.getInstance()
}