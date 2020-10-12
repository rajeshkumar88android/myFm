package com.fm.lastfm

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ApiClient {
    var BASE_URL: String = "http://ws.audioscrobbler.com/"
    val getClient: ApiInterface
        get() {
            val gson = GsonBuilder().setLenient().create()
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(
                GsonConverterFactory.create(gson)).addConverterFactory(ScalarsConverterFactory.create()).build()
            return retrofit.create(ApiInterface::class.java)
        }
}