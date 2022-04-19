package com.example.retrofit.data.networkcall

import com.example.retrofit.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    fun getInstance(): RetrofitClient = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
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