package com.example.albums.Interface

import com.example.albums.Model.AlbumDataItem
import com.example.albums.Model.PhotosDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("albums")
    fun getAlbums(): Call<List<AlbumDataItem>>

    @GET("photos")
    fun getPhotos(@Query("albumId") photosId: Int): Call<List<PhotosDataItem>>
}
