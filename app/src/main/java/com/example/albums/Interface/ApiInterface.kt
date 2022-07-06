package com.example.albums.Interface

import com.example.albums.Model.AlbumDataItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("albums")
    fun getData(): Call<List<AlbumDataItem>>
}