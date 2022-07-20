package com.example.albums.retrofit

import com.example.albums.model.AlbumDataItem
import com.example.albums.model.PhotosDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("albums")
    fun getAlbums(): Call<List<AlbumDataItem>>

    @GET("photos")
    fun getPhotos(@Query("albumId") photosId: Int): Call<List<PhotosDataItem>>
}
