package com.example.albums.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotosDataItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
):Parcelable
