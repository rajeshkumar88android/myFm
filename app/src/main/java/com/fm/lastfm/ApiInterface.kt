package com.fm.lastfm

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName

interface ApiInterface {
    @GET("2.0/")
    fun getPhotos(@Query("method") method: String, @Query("album") album: String, @Query("api_key") apiKey: String,
                  @Query("format") format: String): Call <SearchResponse>

}