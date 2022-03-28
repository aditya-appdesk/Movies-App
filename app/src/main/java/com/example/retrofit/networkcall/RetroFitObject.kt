package com.example.retrofit.networkcall

import com.example.retrofit.utill.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitObject {
    fun getInstance(): RetrofitClient = Retrofit.Builder()
        .baseUrl(Constants.BaseUrl)
        .client(
            OkHttpClient.Builder()
                .apply {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }

                .build())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(RetrofitClient::class.java)
}