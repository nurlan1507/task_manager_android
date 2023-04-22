package com.nurlan1507.task_manager_mobile.restService

import com.google.gson.GsonBuilder
import com.nurlan1507.task_manager_mobile.feature_users.api.AuthService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestService {
    companion object{
        val BASE_URL = "http://3.83.68.211:3000/"
        val okHttpClient = OkHttpClient.Builder().callTimeout(15, TimeUnit.SECONDS).build()
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        val authService = retrofit.create(AuthService::class.java)
    }
}