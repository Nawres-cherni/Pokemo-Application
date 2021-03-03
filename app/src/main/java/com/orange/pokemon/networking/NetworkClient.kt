package com.orange.pokemon.networking

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient {

    private val baseUrl = "http://gist.githubusercontent.com/"

    fun getRetrofit(): Retrofit {

        val httpLog = HttpLoggingInterceptor()
        httpLog.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(Interceptor {
                val request = it.request()
                val newRequest = request.newBuilder()
                    .addHeader("authorization", "TOKEN")
                    .build()
                return@Interceptor it.proceed(newRequest)
            })
            .addInterceptor(httpLog)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}